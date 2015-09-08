package vinixworld.vtestserver;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.common.collect.Maps;
import dao.daoImp.ChatMsjDaoImp;
import entityes.ChatMsj;
import java.text.ParseException;
import java.util.Calendar;


import java.util.Date;

@ServerEndpoint("/chat")
public class SocketServer {

	// set to store all the live sessions
	private static final Set<Session> sessions = Collections
			.synchronizedSet(new HashSet<Session>());
        private boolean isLoged = false;
	// Mapping between session and person name
	private static final HashMap<String, String> nameSessionPair = new HashMap<String, String>();

	private JSONUtils jsonUtils = new JSONUtils();
        private ChatMsjDaoImp cmdi = new ChatMsjDaoImp();
	// Getting query params
	public static Map<String, String> getQueryMap(String query) {
		Map<String, String> map = Maps.newHashMap();
		if (query != null) {
			String[] params = query.split("&");
			for (String param : params) {
				String[] nameval = param.split("=");
				map.put(nameval[0], nameval[1]);
			}
		}
		return map;
	}

	/**
	 * Called when a socket connection opened
	 * */
	@OnOpen
	public void onOpen(Session session) throws ParseException, IOException {
                
		System.out.println(session.getId() + " acaba de entrar");

		Map<String, String> queryParams = getQueryMap(session.getQueryString());

		String name = "";

		if (queryParams.containsKey("name")) {

			// Getting client name via query param
			name = queryParams.get("name");
			try {
				name = URLDecoder.decode(name, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			// Mapping client name and session id
			nameSessionPair.put(session.getId(), name);
		}

		// Adding session to session list
		sessions.add(session);
                
		try {
			// Sending session id to the client that just connected
			session.getBasicRemote().sendText(
					jsonUtils.getClientDetailsJson(session.getId(),
							"tus datos de sesion son"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Notifying all the clients about new person joined
		sendMessageToAll(session.getId(), name, " acaba de entrar!", true,
				false);
                
                
                for (ChatMsj item : cmdi.getLasts()) {
                    //System.out.println(item.getId());
                   String msg = null;
                try {
			JSONObject jObj = new JSONObject(item.getMsj());
			msg = jObj.getString("message");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		// Sending the message to all clients
		session.getBasicRemote().sendText(
					jsonUtils.getSendAllLastMessageJson(item.getUid().toString(), item.getUsername(), item.getMsj()));
            }

	}

	/**
	 * method called when new message received from any client
	 * 
	 * @param message
	 *            JSON message from client
	 * */
	@OnMessage
	public void onMessage(String message, Session session) {

		System.out.println("Mensaje de " + session.getId() + ": " + message);

		String msg = null;

		// Parsing the json and getting message
		try {
			JSONObject jObj = new JSONObject(message);
			msg = jObj.getString("message");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		// Sending the message to all clients
		sendMessageToAll(session.getId(), nameSessionPair.get(session.getId()),
				msg, false, false);
                ChatMsj cm = new ChatMsj();
                cm.setDate(new Date());
                cm.setMsj(msg);
                cm.setUid(session.getId());
                cm.setId(0);
                cm.setUsername(nameSessionPair.get(session.getId()));
                System.out.println("mensaje: "+cm.getMsj());
                System.out.println("uid: "+cm.getUid());
                System.out.println(session.getId());
                cmdi.save(cm);
	}

	/**
	 * Method called when a connection is closed
	 * */
	@OnClose
	public void onClose(Session session) {

		System.out.println(session.getId() + " ha salido");

		// Getting the client name that exited
		String name = nameSessionPair.get(session.getId());

		// removing the session from sessions list
		sessions.remove(session);

		// Notifying all the clients about person exit
		sendMessageToAll(session.getId(), name, " ha salido!", false,
				true);

	}

	/**
	 * Method to send message to all clients
	 * 
	 * @param sessionId
	 * @param message
	 *            message to be sent to clients
	 * @param isNewClient
	 *            flag to identify that message is about new person joined
	 * @param isExit
	 *            flag to identify that a person left the conversation
	 * */
	private void sendMessageToAll(String sessionId, String name,
			String message, boolean isNewClient, boolean isExit) {

		// Looping through all the sessions and sending the message individually
		for (Session s : sessions) {
			String json = null;

			// Checking if the message is about new client joined
			if (isNewClient) {
				json = jsonUtils.getNewClientJson(sessionId, name, message,
						sessions.size());

			} else if (isExit) {
				// Checking if the person left the conversation
				json = jsonUtils.getClientExitJson(sessionId, name, message,
						sessions.size());
			} else {
				// Normal chat conversation message
				json = jsonUtils
						.getSendAllMessageJson(sessionId, name, message);
			}

			try {
				System.out.println("Enviando mensaje a: " + sessionId + ", "
						+ json);

				s.getBasicRemote().sendText(json);
			} catch (IOException e) {
				System.out.println("error enviando. " + s.getId() + ", "
						+ e.getMessage());
				e.printStackTrace();
			}
		}
	}
        private void sendMessageTo(Session session, String name,
			String message, boolean isNewClient, boolean isExit){
            try {
			// Sending session id to the client that just connected
			session.getBasicRemote().sendText(
					jsonUtils
						.getSendAllMessageJson(session.getId(), name, message));
		} catch (IOException e) {
			e.printStackTrace();
		}
           
        }
        private void getUserUid(String sesionId){
         
        }
        
        
}