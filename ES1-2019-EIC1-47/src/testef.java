import java.io.IOException;

import javax.swing.JFrame;

public class testef {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
			JFrame j = new JFrame("Ficheiro");
			LeituraFicheiro t = new LeituraFicheiro();
			t.CorreFicheiro();
			j.setSize(500, 500);
			j.setVisible(true);
			j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			j.add(t);
		}

}
