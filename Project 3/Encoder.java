
public class Encoder implements IHuffConstants, IHuffEncoder {
	TreeMaker tree;
	String codes[];

	@Override
	public void makeTable(ITreeMaker treeMaker) {
		tree = (TreeMaker) treeMaker;
		codes = tree.getCodes();
	}

	@Override
	public String getCode(int i) {
		return codes[i];
	}

}
