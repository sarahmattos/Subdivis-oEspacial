import java.util.ArrayList;

public class bspCutResult {
	public ArrayList<Boid> bFolha1, bFolha2 = new ArrayList<Boid>();
	public int count;
	public int media;
	public bspCutResult(ArrayList<Boid> bFolha1, ArrayList<Boid> bFolha2, int _count, int _media) {

		this.bFolha1 = bFolha1;
		this.bFolha2 = bFolha2;
		this.count = _count;
		this.media = _media;
	}

}
