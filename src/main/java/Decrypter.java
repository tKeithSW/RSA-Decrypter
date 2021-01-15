import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Decrypter {
	public static Gson gson = new Gson();

	public static Details deserialiseJson(String json){
		Details details = null;
		try {
			details = gson.fromJson(new FileReader(json), Details.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return details;
	}

	public static String serialiseToJson(Object o){
		return new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create().toJson(o); 
	}

	public static void serialiseStringToFile (String json, String filename){
		try {
			FileWriter writer = new FileWriter(filename);
			writer.write(json);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			System.err.println("Write to file fail!");
			e.printStackTrace();
		}
		System.out.println("Write to file successful!");
	}

	public static void main(String[] args) {
		Details details = deserialiseJson("input.json");
		Ddtails.start();
		String output = serialiseToJson(Details);
		System.out.println(output);
		serialiseStringToFile(output, "output.json");
	}

}