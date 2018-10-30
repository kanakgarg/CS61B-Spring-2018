public class Planet{
	public double xxPos, yyPos, xxVel, yyVel, mass;
	public String imgFileName;
	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p){
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p){
		double val_x = this.xxPos - p.xxPos;
		double val_y = this.yyPos - p.yyPos;
		return Math.sqrt(val_x*val_x + val_y*val_y);
	}

	public double calcForceExertedBy(Planet p){
		double r = this.calcDistance(p);
		double G = 6.67 * Math.pow(10, -11);

		return  G*p.mass *this.mass/(r*r);

	}

	public double calcForceExertedByX(Planet p){

		return  this.calcForceExertedBy(p)*(p.xxPos-this.xxPos)/this.calcDistance(p);

	}

	public double calcForceExertedByY(Planet p){

		return  this.calcForceExertedBy(p)*(p.yyPos-this.yyPos)/this.calcDistance(p);

	}

	public double calcNetForceExertedByX(Planet[] p){
		int iter = 0;
		double FnetX = 0;

		while(iter < p.length){
			if(this.equals(p[iter])){
				iter++;
				continue;
			}
			FnetX += this.calcForceExertedByX(p[iter]);
			iter++;
		}

		return FnetX;

	}

	public double calcNetForceExertedByY(Planet[] p){
		int iter = 0;
		double FnetY = 0;

		while(iter < p.length){
			if(this.equals(p[iter])){
				iter++;
				continue;
			}
			FnetY += this.calcForceExertedByY(p[iter]);
			iter++;
		}

		return FnetY;
	}

	public void update(double dt, double fX, double fY){
		double anetX = fX/this.mass,  anetY = fY/this.mass;
		this.xxVel = this.xxVel + dt*anetX;
		this.yyVel = this.yyVel + dt*anetY;
		this.xxPos = this.xxPos + dt*xxVel;
		this.yyPos = this.yyPos + dt*yyVel;

	}

	public void draw(){
		StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
	}
}