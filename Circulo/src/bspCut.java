import java.util.ArrayList;

public class bspCut {
	public int count;

	public bspCutResult getResult(ArrayList<Boid> b) {
		count++;

		if (count - 1 % 2 == 0) {
			return horizontalCut(b);
		} else {
			return verticalCut(b);
		}

	}

	public bspCutResult horizontalCut(ArrayList<Boid> b) {

		int media = 0;
		for (int i = 0; i < b.size(); i++) {
			media += b.get(i).getPositionY() + b.get(i).getAltura();
		}
		media /= b.size();
		ArrayList<Boid> f1 = new ArrayList<Boid>();
		ArrayList<Boid> f2 = new ArrayList<Boid>();
		for (int i = 0; i < b.size(); i++) {
			if (true || media < b.get(i).getPositionY() || media > b.get(i).getPositionY() + b.get(i).getAltura()) {

				for (int j = 0; j < b.size(); j++) {

					if (media > b.get(j).getPositionY() + b.get(j).getAltura()) {
						f1.add(b.get(j));
					} else {
						f2.add(b.get(j));
					}
					
				}
				return new bspCutResult(f1, f2);
			}
			// media+= b.get(i).getPositionY()+b.get(i).getAltura();
		}
		return verticalCut(b);
	}

	public bspCutResult verticalCut(ArrayList<Boid> b) {
		return horizontalCut(b);
	}
}
