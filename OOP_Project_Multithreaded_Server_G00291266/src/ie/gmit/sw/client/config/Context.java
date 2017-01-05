package ie.gmit.sw.client.config;


/* A context represents the entire scope of an application, i.e.
 * we can assign "global variables" to a context.
 * 
 * This is a "bean class", containing a constructor and accessor
 * methods only.
 */
public class Context {
	//Variables--------------------------------------------------------------------------
	public static final String CONF_FILE="client-config.xml";
	private String username;
	private String host;
	private int port;
	private String dir;
	
	//constructor--------------------------------------------------------------------------
	public Context() {
		super();
	}

	//Getters & Setters--------------------------------------------------------------------------
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String downloadDir) {
		this.dir = downloadDir;
	}
	
	//Overriden methods--------------------------------------------------------------------------
	@Override
	public String toString() {
		return "Context [username=" + username + ", host=" + host + ", port=" + port + ", dir=" + dir + "]";
	}

}
