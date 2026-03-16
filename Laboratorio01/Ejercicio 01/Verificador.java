public class Verificador {

    public static boolean seSobreponen(Rectangulo r1, Rectangulo r2) {
        double izquierda1 = Math.min(r1.getEsquina1().getX(), r1.getEsquina2().getX());
        double derecha1   = Math.max(r1.getEsquina1().getX(), r1.getEsquina2().getX());
        double abajo1     = Math.min(r1.getEsquina1().getY(), r1.getEsquina2().getY());
        double arriba1    = Math.max(r1.getEsquina1().getY(), r1.getEsquina2().getY());

        double izquierda2 = Math.min(r2.getEsquina1().getX(), r2.getEsquina2().getX());
        double derecha2   = Math.max(r2.getEsquina1().getX(), r2.getEsquina2().getX());
        double abajo2     = Math.min(r2.getEsquina1().getY(), r2.getEsquina2().getY());
        double arriba2    = Math.max(r2.getEsquina1().getY(), r2.getEsquina2().getY());

        return (derecha1 > izquierda2 &&
                derecha2 > izquierda1 &&
                arriba1 > abajo2 &&
                arriba2 > abajo1);
    }

    public static boolean seJuntan(Rectangulo r1, Rectangulo r2) {
        double izquierda1 = Math.min(r1.getEsquina1().getX(), r1.getEsquina2().getX());
        double derecha1   = Math.max(r1.getEsquina1().getX(), r1.getEsquina2().getX());
        double abajo1     = Math.min(r1.getEsquina1().getY(), r1.getEsquina2().getY());
        double arriba1    = Math.max(r1.getEsquina1().getY(), r1.getEsquina2().getY());

        double izquierda2 = Math.min(r2.getEsquina1().getX(), r2.getEsquina2().getX());
        double derecha2   = Math.max(r2.getEsquina1().getX(), r2.getEsquina2().getX());
        double abajo2     = Math.min(r2.getEsquina1().getY(), r2.getEsquina2().getY());
        double arriba2    = Math.max(r2.getEsquina1().getY(), r2.getEsquina2().getY());

        boolean tocanVertical =
                (derecha1 == izquierda2 || izquierda1 == derecha2) &&
                        (arriba1 >= abajo2 && arriba2 >= abajo1);

        boolean tocanHorizontal =
                (arriba1 == abajo2 || abajo1 == arriba2) &&
                        (derecha1 >= izquierda2 && derecha2 >= izquierda1);

        return !seSobreponen(r1, r2) && (tocanVertical || tocanHorizontal);
    }

    public static boolean sonDisjuntos(Rectangulo r1, Rectangulo r2) {
        return !seSobreponen(r1, r2) && !seJuntan(r1, r2);
    }
}