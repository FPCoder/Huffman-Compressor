
/**
 * The interface for the model that can be attached
 * to a HuffmanView. Most of the work done in huffing
 * (and unhuffing) will be via a class that implements
 * this interface. The interface may need to be extended
 * depending on the design of the huffman program.
 * <P>
 * @author Owen Astrachan
 *
 */

import java.io.InputStream;
import java.io.OutputStream;
import java.io.File;

public interface IHuffModel extends IHuffConstants {
    /**
     * Display all encodings (via the associated view).
     */
    public void showCodings();
    
    /**
     * Display all chunk/character counts (via the associated view).
     */
    public void showCounts();
    
    /**
     * Initialize state via an input stream. The stream most
     * likely comes from a view, it's NOT a BitInputStream
     * @param stream is an input stream for initializing state of this model
     */
    public void initialize(InputStream stream);
    
    /**
     * Write a compressed version of the data read
     * by the InputStream parameter, -- if the stream is
     * not the same as the stream last passed
     * to initialize, then compression won't be optimal, but will still
     * work. If force is false, compression only occurs if it saves
     * space. If force is true compression results even if no bits are saved.
     * @param stream is the input stream to be compressed
     * @param file specifes the file to be written with compressed data
     * @param force indicates if compression forced
     */
    public void write(InputStream stream, File file, boolean force);
    
    /**
     * Make sure this model communicates with some view.
     * @param viewer is the view for communicating.
     */
    public void setViewer(HuffViewer viewer);
    
    /**
     * Uncompress a previously compressed file.
     * @param in is the compressed file to be uncompressed
     * @param out is where the uncompressed bits will be written
     */
    public void uncompress(InputStream in, OutputStream out);
    
}
