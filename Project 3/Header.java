import java.io.IOException;

public class Header implements IHuffHeader {

	@Override
	public int headerSize() {
		// FIXME: unsure if right implementation
		return BITS_PER_INT;
	}

	@Override
	public void writeHeader(BitOutputStream out) {
		out.write(BITS_PER_INT, MAGIC_NUMBER);
	}

	@Override
	public ITreeMaker readHeader(BitInputStream in) throws IOException {
		int magic = in.read(BITS_PER_INT);
		if (magic != MAGIC_NUMBER) {
			throw new IOException("magic number not right");
		}
		return null;
	}

}
