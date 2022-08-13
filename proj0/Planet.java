
public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public final static double G = 6.67e-11;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Planet(Planet p){
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }
    public double calcDistance(Planet p){
       return Math.sqrt((p.xxPos-xxPos)*(p.xxPos-xxPos)+
                (p.yyPos-yyPos)*(p.yyPos-yyPos));

    }

    public double calcForceExertedBy(Planet p){
        double distance = calcDistance(p);
        return G*mass*p.mass/(distance*distance);

    }
    public double calcForceExertedByX(Planet p){
        double force = calcForceExertedBy(p);
        double distance = calcDistance(p);
        double x = p.xxPos - xxPos;
        return force*x/distance;
    }
    public double calcForceExertedByY(Planet p){
        double force = calcForceExertedBy(p);
        double distance = calcDistance(p);
        double y = p.yyPos - yyPos;
        return force*y/distance;
    }
    public double calcNetForceExertedByX(Planet[] allp){
        double sum = 0;
        for(Planet p : allp){
            if(p.equals(this)) continue;
            sum += calcForceExertedByX(p);
        }
        return sum;
    }
    public double calcNetForceExertedByY(Planet[] allp){
        double sum = 0;
        for(Planet p : allp){
            if(p.equals(this)) continue;
            sum += calcForceExertedByY(p);
        }
        return sum;
    }

    public void update(double time,double fx,double fy){
        double ax = fx/mass;
        double ay = fy/mass;
        xxVel = xxVel + ax*time;
        yyVel = yyVel + ay*time;
        xxPos = xxPos + xxVel*time;
        yyPos = yyPos + yyVel*time;


    }
    public void draw(){
        StdDraw.picture(xxPos,yyPos,"images/"+imgFileName);
        //StdDraw.show();
       // StdDraw.pause(2000);
    }










}
