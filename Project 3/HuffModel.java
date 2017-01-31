import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;

public class HuffModel implements IHuffModel {
	private CharCounter chars;
	private HuffViewer vw;
	private String codes[] = new String[ALPH_SIZE + 1];
	
	HuffModel() {
		chars = new CharCounter();
	}
	
	public CharCounter getChars() {
		return chars;
	}
	
	public void setCodes(String[] c) {
		codes = c;
	}

	@Override
	public void showCodings() {
		ArrayList<String> list = new ArrayList<>();
		if (codes == null) {
			System.out.println("no codes");
			return;
		}
		
		for (int i = 0; i < 256; ++i) {
			if (codes[i] == null) {
				list.add(i + " " + 0);
			}
			else {
				list.add(i + " " + codes[i]);
			}
		}
		vw.update(list);

	}

	@Override
	public void showCounts() {
		ArrayList<String> list = new ArrayList<>();
		for (int i = 0; i < chars.size(); ++i) {
			list.add(i + " " + chars.getCount(i));
		}
		vw.update(list);

	}

	@Override
	public void initialize(InputStream stream) {
		try {
			chars.countAll(stream);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void write(InputStream stream, File file, boolean force) {
		BitInputStream st = new BitInputStream(stream);
		BitOutputStream result = new BitOutputStream(file.getName());
		int i = 0; // ascii of next character
		int j = 0; // used to navigate code strings
		int val = 0; // assigned respective value (1, 0) of individual values in string
		Header hd = new Header();
		
		hd.writeHeader(result); // add header to stream
		
		try {
			while ((i = st.read()) > 0) {
				j = 0;
				System.out.println("code: " + codes[i]);
				while (j < codes[i].length()) {
					if (codes[i].substring(j, j+1).equals("1")) {
						System.out.println("if");
						val = (int)('1') - (int)('0');
					}
					else {
						System.out.println("else");
						val = 0;
					}
					System.out.println("str: " + codes[i].substring(j, j+1) + " val: " + val);
					result.write(1, val);
					j++;
				}
				System.out.println("i: " + i);
			}
			result.close();
			st.close();
		}
		catch (IOException e) {
			e.getStackTrace();
		}
	}

	@Override
	public void setViewer(HuffViewer viewer) {
		vw = viewer;

	}

	@Override
	public void uncompress(InputStream in, OutputStream out) {
		// TODO Auto-generated method stub

	}

}
