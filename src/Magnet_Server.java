import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Magnet_Server 
{
	private ServerSocket serverSocket;

	public void start(int port) {
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("Server is lisinig on port "+ port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		while (true)
			try {
				new EchoClientHandler(serverSocket.accept()).start();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	public void stop() {
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static class EchoClientHandler extends Thread {
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		String dbUrl = "jdbc:mysql://localhost:3306/magnet-db";
		String user = "student";
		String pass = "studnt";
		private Socket clientSocket;
		private PrintWriter out;
		private DataInputStream in = null;
		protected DataOutputStream outputStream = null;

		public EchoClientHandler(Socket socket) {
			this.clientSocket = socket;
			run();
		}

		public void run() {
			try {
				System.out.println("run1");
				out = new PrintWriter(clientSocket.getOutputStream(), true);
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				System.out.println("run2");
				 in = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
                 outputStream = new DataOutputStream(clientSocket.getOutputStream());
                 out = new PrintWriter(clientSocket.getOutputStream());
                 
				String response = "";
				response = new BufferedReader(new InputStreamReader(in)).readLine();
			
				switch (response) {
				case "insert":
					double id;
					String city;
					String street;
					int street_num;
					String infrastructure;
					String isp;
					String infrastructure_type;
					double download_speed;
					double upload_speed;
					double ping;
					double paid_speed;
					int disconnections;
					double internet_score;
					while(response!="over") {
					response = new BufferedReader(new InputStreamReader(in)).readLine();
					id= Double.parseDouble(response);
					response = new BufferedReader(new InputStreamReader(in)).readLine();
					city=response;
					response = new BufferedReader(new InputStreamReader(in)).readLine();
					street=response;
					response = new BufferedReader(new InputStreamReader(in)).readLine();
					street_num=Integer.parseInt(response);
					response = new BufferedReader(new InputStreamReader(in)).readLine();
					infrastructure=response;
					response = new BufferedReader(new InputStreamReader(in)).readLine();
					isp= response;
					response = new BufferedReader(new InputStreamReader(in)).readLine();
					infrastructure_type=response;
					response = new BufferedReader(new InputStreamReader(in)).readLine();
					download_speed=Double.parseDouble(response);
					response = new BufferedReader(new InputStreamReader(in)).readLine();
					upload_speed=Double.parseDouble(response);
					response = new BufferedReader(new InputStreamReader(in)).readLine();
					ping=Double.parseDouble(response);
					response = new BufferedReader(new InputStreamReader(in)).readLine();
					paid_speed=Double.parseDouble(response);
					response = new BufferedReader(new InputStreamReader(in)).readLine();
					disconnections=Integer.parseInt(response);
					response = new BufferedReader(new InputStreamReader(in)).readLine();
					internet_score= Double.parseDouble(response);
					Clinet client = new Clinet(id,city,street,street_num,infrastructure,isp,infrastructure_type,download_speed,upload_speed,ping,paid_speed,disconnections,internet_score);
					insert_clinet(client);
					}
					break;
				case "update":
				double id2;
					String city2;
					String street2;
					int street_num2;
					String infrastructure2;
					String isp2;
					String infrastructure_type2;
					double download_speed2;
					double upload_speed2;
					double ping2;
					double paid_speed2;
					int disconnections2;
					double internet_score2;
					while(response!="over") {
					response = new BufferedReader(new InputStreamReader(in)).readLine();
					id2= Double.parseDouble(response);
					response = new BufferedReader(new InputStreamReader(in)).readLine();
					city2=response;
					response = new BufferedReader(new InputStreamReader(in)).readLine();
					street2=response;
					response = new BufferedReader(new InputStreamReader(in)).readLine();
					street_num2=Integer.parseInt(response);
					response = new BufferedReader(new InputStreamReader(in)).readLine();
					infrastructure2=response;
					response = new BufferedReader(new InputStreamReader(in)).readLine();
					isp2= response;
					response = new BufferedReader(new InputStreamReader(in)).readLine();
					infrastructure_type2=response;
					response = new BufferedReader(new InputStreamReader(in)).readLine();
					download_speed2=Double.parseDouble(response);
					response = new BufferedReader(new InputStreamReader(in)).readLine();
					upload_speed2=Double.parseDouble(response);
					response = new BufferedReader(new InputStreamReader(in)).readLine();
					ping2=Double.parseDouble(response);
					response = new BufferedReader(new InputStreamReader(in)).readLine();
					paid_speed2=Double.parseDouble(response);
					response = new BufferedReader(new InputStreamReader(in)).readLine();
					disconnections2=Integer.parseInt(response);
					response = new BufferedReader(new InputStreamReader(in)).readLine();
					internet_score2= Double.parseDouble(response);
					Clinet client2 = new Clinet(id2,city2,street2,street_num2,infrastructure2,isp2,infrastructure_type2,download_speed2,upload_speed2,ping2,paid_speed2,disconnections2,internet_score2);
					update_clinet(client2);
					}
					break;
				case "rec":
				double id3;
					String city3;
					String street3;
					int street_num3;
					String infrastructure3;
					String isp3;
					String infrastructure_type3;
					double download_speed3;
					double upload_speed3;
					double ping3;
					double paid_speed3;
					int disconnections3;
					double internet_score3;
					while(response!="over") {
					response = new BufferedReader(new InputStreamReader(in)).readLine();
					id3= Double.parseDouble(response);
					response = new BufferedReader(new InputStreamReader(in)).readLine();
					city3=response;
					response = new BufferedReader(new InputStreamReader(in)).readLine();
					street3=response;
					response = new BufferedReader(new InputStreamReader(in)).readLine();
					street_num3=Integer.parseInt(response);
					response = new BufferedReader(new InputStreamReader(in)).readLine();
					infrastructure3=response;
					response = new BufferedReader(new InputStreamReader(in)).readLine();
					isp3= response;
					response = new BufferedReader(new InputStreamReader(in)).readLine();
					infrastructure_type3=response;
					response = new BufferedReader(new InputStreamReader(in)).readLine();
					download_speed3=Double.parseDouble(response);
					response = new BufferedReader(new InputStreamReader(in)).readLine();
					upload_speed3=Double.parseDouble(response);
					response = new BufferedReader(new InputStreamReader(in)).readLine();
					ping3=Double.parseDouble(response);
					response = new BufferedReader(new InputStreamReader(in)).readLine();
					paid_speed3=Double.parseDouble(response);
					response = new BufferedReader(new InputStreamReader(in)).readLine();
					disconnections3=Integer.parseInt(response);
					response = new BufferedReader(new InputStreamReader(in)).readLine();
					internet_score3= Double.parseDouble(response);
					Clinet client3 = new Clinet(id3,city3,street3,street_num3,infrastructure3,isp3,infrastructure_type3,download_speed3,upload_speed3,ping3,paid_speed3,disconnections3,internet_score3);
					String rec = recommendation(client3);
					out.println(rec);
					//return to the clinet the rec
					}
					break;
				case "":
					out.println("dont send emptay masage");
					break;
				}
				
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		public void insert_clinet(Clinet data){
			try {
				// 1. Get a connection to database
				myConn = DriverManager.getConnection(dbUrl, user, pass);

				System.out.println("Database connection successful!\n");

				// 3. insert new clinet
				System.out.println("Inserting a new clinet to database");
				PreparedStatement myStmt =
						myConn.prepareStatement("insert into clinet (id,city,street,street_num,infrastructure,isp,infrastructure_type,download_speed,upload_speed,ping,paid_speed,disconnections,internet_score)"
								+"values (?,?,?,?,?,?,?,?,?,?,?,?,?)");
				myStmt.setDouble(1, data.id);
				myStmt.setString(2, data.city);
				myStmt.setString(3, data.street);
				myStmt.setDouble(4, data.street_num);
				myStmt.setString(5, data.infrastructure);
				myStmt.setString(6, data.isp);
				myStmt.setString(7, data.infrastructure_type);
				myStmt.setDouble(8, data.download_speed);
				myStmt.setDouble(9, data.upload_speed);
				myStmt.setDouble(10,data.ping);
				myStmt.setDouble(11, data.paid_speed);
				myStmt.setDouble(12,data.disconnections);
				myStmt.setDouble(13,data.internet_score);
				//Execute update query
				myStmt.executeUpdate();
				System.out.println("successfuly inserted a clinet");
				// 4. Verify the client was added to the db
				myRs = myStmt.executeQuery("select * from clinet");



				// 5. Process the result set
				while (myRs.next()) {
					System.out.println(myRs.getString("id"));
				}
			}
			catch (Exception exc) {
				exc.printStackTrace();
			}

		}

		public void update_clinet(Clinet data){

			try {
				// Get a connection to database
				myConn = DriverManager.getConnection(dbUrl, user, pass);

				//Statement to update a clinet Parameters.
				PreparedStatement myStmt =
						myConn.prepareStatement("update clinet" + "set city = ? and street = ? and street_num = ? and infrastructure = ? and isp = ? and infrastructure_type = ? and download_speed = ? and upload_speed = ? and ping = ? and paid_speed = ? and disconnections = ? and internet_score = ?" + "where id = ?" );
				myStmt.setString(1, data.city);
				myStmt.setString(2, data.street);
				myStmt.setDouble(3, data.street_num);
				myStmt.setString(4, data.infrastructure);
				myStmt.setString(5, data.isp);
				myStmt.setString(6, data.infrastructure_type);
				myStmt.setDouble(7, data.download_speed);
				myStmt.setDouble(8, data.upload_speed);
				myStmt.setDouble(9,data.ping);
				myStmt.setDouble(10, data.paid_speed);
				myStmt.setDouble(11,data.disconnections);
				myStmt.setDouble(12,data.internet_score);
				myStmt.setDouble(13, data.id);

				//Execute update query
				myRs = myStmt.executeQuery();



			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		public double clinet_score(Clinet data) {
			double disconnections_score;
			double download_score;
			double upload_score;
			double semi_upload_score;
			double ping_score;
			double internet_score;
			disconnections_score= ((((-50/7)*data.disconnections)+100)/100)*4;
			download_score= (data.download_speed/data.paid_speed)*3;
			semi_upload_score=(data.upload_speed/data.paid_speed)*100;
			upload_score=(((100/7)*(semi_upload_score-10))+100)/100;
			ping_score= ((((-5/6)*data.ping)+125)/100)*2;
			internet_score = disconnections_score + download_score + upload_score + ping_score;
			return internet_score;
		}

		public String recommendation(Clinet client){
			try {
			double[] infrastructure_score = new double[10];
			int num_clinets_infrastructure = 0;
			PreparedStatement myStmt = myConn.prepareStatement("select internet_Score from clinet where city = ? and street = ? and street_num = ? and infrastructure = ?");
			myStmt.setString(1, client.city);
			myStmt.setString(2, client.street);
			myStmt.setDouble(3, client.street_num);
			myStmt.setString(4,"bezeq");
			myRs = myStmt.executeQuery();
			while(myRs.next()){
				num_clinets_infrastructure++;
				infrastructure_score[0]+=myRs.getDouble("internet_score");
			}
			infrastructure_score[0]=infrastructure_score[0]/num_clinets_infrastructure;


			num_clinets_infrastructure=0;
			myStmt.setString(1, client.city);
			myStmt.setString(2, client.street);
			myStmt.setDouble(3, client.street_num);
			myStmt.setString(4,"hot");
			myRs = myStmt.executeQuery();
			while(myRs.next()){
				num_clinets_infrastructure++;
				infrastructure_score[1]+=myRs.getDouble("internet_score");
			}
			infrastructure_score[1]=infrastructure_score[1]/num_clinets_infrastructure;




			num_clinets_infrastructure=0;
			myStmt.setString(1, client.city);
			myStmt.setString(2, client.street);
			myStmt.setDouble(3, client.street_num);
			myStmt.setString(4,"unlimited");
			myRs = myStmt.executeQuery();
			while(myRs.next()){
				num_clinets_infrastructure++;
				infrastructure_score[2]+=myRs.getDouble("internet_score");;
			}
			infrastructure_score[2]=infrastructure_score[2]/num_clinets_infrastructure;





			num_clinets_infrastructure=0;
			myStmt.setString(1, client.city);
			myStmt.setString(2, client.street);
			myStmt.setDouble(3, client.street_num);
			myStmt.setString(4,"partnar");
			myRs = myStmt.executeQuery();
			while(myRs.next()){
				num_clinets_infrastructure++;
				infrastructure_score[3]+=myRs.getDouble("internet_score");
			}
			infrastructure_score[3]=infrastructure_score[3]/num_clinets_infrastructure;




			num_clinets_infrastructure=0;
			myStmt.setString(1, client.city);
			myStmt.setString(2, client.street);
			myStmt.setDouble(3, client.street_num);
			myStmt.setString(4,"cellcom");
			myRs = myStmt.executeQuery();
			while(myRs.next()){
				num_clinets_infrastructure++;
				infrastructure_score[4]+=myRs.getDouble("internet_score");
			}
			infrastructure_score[4]=infrastructure_score[4]/num_clinets_infrastructure;
			
			String internet_recommendation = best_infrastructure(infrastructure_score);
			return internet_recommendation;
			
			
			
			
			
			
			} catch (SQLException e) {

				e.printStackTrace();
				return "";
			}
		}

		public static String best_infrastructure(double array []) {
			double max = array[0];
			int count=0;
			for(int x=0;x<array.length;x++) {
				if(array[x]>max) {
					max=array[x];
				count=x;
				}
			}
			if(count==0 || count!=0)        
				return "bezeq";
			else if(count==1)
				return "hot";
			else if(count==2)
				return "unlimited";
			else if(count==3)
				return "partnar";
			else if(count==4)
				return "cellcom";
			else
			return "Eror";
		}
	}
	public static void main(String args[])
    {
		Magnet_Server MS = new Magnet_Server();
		MS.start(5000);
		
    }
}