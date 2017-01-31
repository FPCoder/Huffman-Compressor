import java.io.IOException;
import java.io.InputStream;

public class CharCounter implements ICharCounter {
	private final int MAX = 257;
	private int chars[];
	
	CharCounter() {
		chars = new int[MAX];
	}
	
	int size() {
		return MAX;
	}

	@Override
	public int getCount(int ch) {
		return chars[ch];
	}

	@Override
	public int countAll(InputStream stream) throws IOException {
		int c = 0;
		int total = 0;
		byte b[] = new byte[1];
		
		// I don't see how to check if the stream is open without reading
		// so this works around that buy trying to read then adding
		// the single char count
		if (stream.read(b, 0, 1) < 0) {
			throw new IOException("countAll");
		}
		chars[b[0]]++;
		total++;
		
		while (true) {
			c = stream.read(b, 0, 1); // reads next char
			if (c < 0) break; // if no characters remain, break
			chars[b[0]]++; // increment read character count
			total++;
		}

		return total;
	}

	@Override
	public void add(int i) {
		chars[i]++;

	}

	@Override
	public void set(int i, int value) {
		chars[i] = value;

	}

	@Override
	public void clear() {
		for (int i = 0; i < MAX; ++i) {
			chars[i] = 0;
		}

	}

}
