public class NBody{
	private double numPlanets;
	private Planet[] planets;
	private double univSize;


	public static double readRadius(String file){
        In in = new In(file);
        in.readLine();
        double univSize = in.readDouble();
        return univSize;
	}

	public static Planet[] readPlanets(String file){
		In in = new In(file);
		Planet[] planets =  new Planet[in.readInt()];
		in.readDouble();
		for(int i = 0; i < planets.length; i++){
			double xxPos = in.readDouble(), yyPos = in.readDouble();
			double xxVel = in.readDouble(), yyVel = in.readDouble();
			double mass =in.readDouble();
			String imgName = in.readString();
			planets[i]= new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgName);
		}
		return planets;
	}

	public static void main(String[] args){
		StdDraw.enableDoubleBuffering();
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double univRadius = readRadius(filename);
		Planet[] planets = readPlanets(filename);
		double time = 0;
		

		while (time < T){
			StdDraw.setScale(-univRadius, univRadius);
			StdDraw.picture(0, 0, "images/starfield.jpg");
			double[] xForces = new double[planets.length];
			double[] yForces = new double[planets.length];
			for(int i = 0; i < planets.length; i++){
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}
			for(int i = 0; i < planets.length; i++){
				planets[i].update(dt, xForces[i], yForces[i]);
				planets[i].draw();
			}

			StdDraw.show();
			StdDraw.pause(10);
			time+=dt;
		}

		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", univRadius);
		for (int i = 0; i < planets.length; i++) {
		    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
		                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}

	}
}
