package webapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import webapp.dao.UserService;
import webapp.model.UserDetails;

@WebServlet("/users/*")
public class MainController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Gson _gson = null;

	UserService service = new UserService();

	// a utility method to send object
	// as JSON response
	private void sendAsJson(HttpServletResponse response, Object obj) throws IOException {

		System.out.println("Object to json" + obj);
		response.setContentType("application/json");
		_gson = new Gson();


		String res = _gson.toJson(obj);

		PrintWriter out = response.getWriter();

		out.print(res);
		out.flush();
	}

	// Get models
	// GET/summary/
	// GET/summary/id
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Map<String, UserDetails>  models;
		String pathInfo = request.getPathInfo();

		if (pathInfo == null || pathInfo.equals("/")) {

			try {
				models = service.getAll();
				sendAsJson(response, models);
				return;
			} catch (Exception e) {
				e.printStackTrace();
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				return;
			}

		}

		String[] splits = pathInfo.split("/");

		if (splits.length != 2) {

			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		String modelId = splits[1];

		/*
		 * if (!models.containsKey(modelId)) {
		 * 
		 * response.sendError(HttpServletResponse.SC_NOT_FOUND); return; }
		 * 
		 * sendAsJson(response, _modelsDb.get(modelId));
		 */
		return;
	}

	
}
