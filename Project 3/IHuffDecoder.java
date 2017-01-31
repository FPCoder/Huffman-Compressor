
/**
 * Uncompress/decode a compressed file, where the compression
 * was done by this suite of classes. The idea is that a
 * class implementing this interface will be constructed/initialized
 * with a Huffman-tree, e.g., as read from the header of a compressed
 * file via a <code>ITreeMaker</code> object. Then the compressed
 * file will be uncompressed via the <code>doDecode</code> method
 * which reads one bit-at-a-time and writes a value when it
 * reaches a leaf of the tree with which the IHuffDecoder
 * object was initialized.
 * <P>
 * @author Owen Astrachan
 *
 */
import java.io.IOException;

public interface IHuffDecoder extends IHuffConstants{
    
    /**
     * Initialize by having access to the tree supplied by the treeMaker
     * parameter so that subsequent calls to <code>doDecode</code> will be
     * able to read one bit-at-a-time and write values encoded in the input
     * file by accessing leaves of the tree appropriately. Presumably the tree
     * is stored in some state accessible to <code>doDecode</code>.
     * @param treeMaker is the source of the tree that will be used to decode
     * a compressed file
     */
    public void initialize(ITreeMaker treeMaker);
    
    /**
     * Read one bit-at-a-time from the input file which is presumed to be a file
     * compressed by this suite of classes. The data stored in the file is written
     * to the output file one char/int/word at a time, e.g., when a leaf of the
     * tree used to initialize this object is reached. The output stops when
     * PSEUDO_EOF is reached (or bits run out)
     * @param input is source of compressed bits, the header has been read already
     * @param output is where uncompressed file is written, BITS_PER_WORD at-a-time
     * assuming that's what is stored in leaves of the tree used for initialization
     * @throws IOException if bits run out before reaching PSEUDO_EOF or other
     * I/O error occurs
     */
    public void doDecode(BitInputStream input, BitOutputStream output) throws IOException;
}
