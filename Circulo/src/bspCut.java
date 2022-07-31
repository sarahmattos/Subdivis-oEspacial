import java.util.ArrayList;

public class bspCut {
	public int count = 0;

	public bspCutResult getResult(ArrayList<Boid> b) {
		count++;
		// corte horizontal ou vertical
		if (count - 1 % 2 == 0) {
			return horizontalCut(b);

		} else {

			return verticalCut(b);
		}

	}

	// corte horizontal
	public bspCutResult horizontalCut(ArrayList<Boid> b) {
		// calculo da media
		int media = 0;
		for (int i = 0; i < b.size(); i++) {
			media += b.get(i).getPositionY() + b.get(i).getAltura();
		}
		media /= b.size();
		// duas listas de boid
		ArrayList<Boid> f1 = new ArrayList<Boid>();
		ArrayList<Boid> f2 = new ArrayList<Boid>();
		
		for (int i = 0; i < b.size(); i++) {
			if (media < b.get(i).getPositionY() || media > b.get(i).getPositionY() + b.get(i).getAltura()) {
				for (int j = 0; j < b.size(); j++) {
					
					if (media > b.get(j).getPositionY() + b.get(j).getAltura()) {
						// se o boid está abaixo da media ela é adicionada na f1
						f1.add(b.get(j));
					} else {
						//senao ela é adicionada à f2
						f2.add(b.get(j));
					}
				}
				//(passa a informação das folhas,count e a media)
				return new bspCutResult(f1, f2, 2, media - b.get(i).getAltura());
			}
		}
		return verticalCut(b);
	}

	// corte vertical
	public bspCutResult verticalCut(ArrayList<Boid> b) {
		// calculo da media
		int media = 0;
		for (int i = 0; i < b.size(); i++) {
			media += b.get(i).getPositionX() + b.get(i).getLargura();
		}
		media /= b.size();
		
		// duas listas de boid
		ArrayList<Boid> f1 = new ArrayList<Boid>();
		ArrayList<Boid> f2 = new ArrayList<Boid>();

		for (int i = 0; i < b.size(); i++) {
			if (true || media < b.get(i).getPositionX() || media > b.get(i).getPositionX() + b.get(i).getLargura()) {

				for (int j = 0; j < b.size(); j++) {
					
					if (media > b.get(j).getPositionX() + b.get(j).getLargura()) {
						// se o boid está a esquerda da media ela é adicionada na f1
						f1.add(b.get(j));
					} else {
						//senão adicionada ao f2
						f2.add(b.get(j));
					}

				}
				//(passa a informação das folhas,count e a media)
				return new bspCutResult(f1, f2, 1, media - b.get(i).getLargura());
			}
		}

		return horizontalCut(b);
	}
}
