import java.io.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
public class FileManager 
{
	public static void saveCars(Car a,String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename)))
        {
        	 writer.write( a.getID() + ";" + a.getBrand() + ";" + a.getModel() + ";"
                     + a.getYear() + ";" + a.getFee() + ";" + a.getPlate() + ";" + a.getStatus());
             writer.newLine();
        }
	}
}
