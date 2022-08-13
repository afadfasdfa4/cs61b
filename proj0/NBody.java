public  class NBody{
    public static double readRadius(String filename){
        In in = new In(filename);
        in.readInt();
        return in.readDouble();


    }
    public static Planet[] readPlanets(String filename){
        In in = new In(filename);
        int num = in.readInt();
        Planet[] planets = new Planet[num];
        in.readDouble();

        for(int i=0;i<num;i++){
            double xxp = in.readDouble();
            double yyp = in.readDouble();
            double xxv = in.readDouble();
            double yyv = in.readDouble();
            double mass = in.readDouble();
            String s = in.readString();
            planets[i] = new Planet(xxp,yyp,xxv,yyv,mass,s);
        }
        return planets;

    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius,radius);
        StdDraw.clear();
        String imageToDraw = "images/starfield.jpg";
        //StdDraw.picture(0, 0, imageToDraw);
        //StdDraw.show();
        double time = 0;

       // StdDraw.enableDoubleBuffering();
      //  StdAudio.play("audio/2001.mid");
        while (time<T) {
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for(int i = 0;i < planets.length;i++){
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for(int i=0;i<planets.length;i++){
                planets[i].update(dt,xForces[i],yForces[i]);
            }
            StdDraw.picture(0, 0, imageToDraw);
            for (Planet p : planets) {
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time += dt;
        }
     //   StdAudio.close();
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }


}