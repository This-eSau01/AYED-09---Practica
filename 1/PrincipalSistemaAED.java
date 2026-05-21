public class PrincipalSistemaAED {

    public static void main(String[] args) {

        System.out.println("((((((((((((((SISTEMA ACADÉMICO AED - UNIVERSIDAD CATO SANTA MARIA \n)))))))))))))))");

        ListaEstudiantes lista = new ListaEstudiantes();
        PilaAcciones pila = new PilaAcciones();
        ColaTurnos cola = new ColaTurnos();
        ArbolABB arbol = new ArbolABB();

    
        Estudiante e1 = new Estudiante(2024002070, "John Menacho", 16.8);
        Estudiante e2 = new Estudiante(2024010710, "Joseph Llaza", 14.5);
        Estudiante e3 = new Estudiante(2024001492, "Salvador Rondan", 19.2);
        Estudiante e4 = new Estudiante(2024232026, "Sammir Huari", 12.9);
        Estudiante e5 = new Estudiante(2024002006, "Javier Angulo", 15);

        lista.agregar(e1);
        pila.push("Se registró al estudiante con codigo 2024002070");
        lista.agregar(e2);
        pila.push("Se registró al estudiante con codigo 2024010710");
        lista.agregar(e3);
        pila.push("Se registró al estudiante con codigo 2024001492");
        lista.agregar(e4);
        pila.push("Se registró al estudiante con codigo 20242320226");
        lista.agregar(e5);
        pila.push("Se registro al estudinte conn el codigo 2024002006 ");


        System.out.println("[LISTA DE ESTUDIANTES]");
        lista.mostrarTodos();

        System.out.println("\n[BUSCAR ESTUDIANTE]");
        int codigoBusqueda = 2024002006;
        Estudiante encontrado = lista.buscarPorCodigo(codigoBusqueda);
        System.out.println("Codigo buscado: " + codigoBusqueda);
        if (encontrado != null) {
            System.out.println("Resultado: " + encontrado.getNombre() + " encontrado.");
            pila.push("Se busco al estudiante con código " + codigoBusqueda);
        } else {
            System.out.println("Estudiante con el codigo brindando no encontrado");
        }

        System.out.println("\n[ELIMINAR ESTUDIANTE]");
        int codigoEliminar = 2024232026;
        boolean eliminado = lista.eliminarPorCodigo(codigoEliminar);
        System.out.println("Código eliminado: " + codigoEliminar);
        if (eliminado) {
            System.out.println("Estudiante eliminado con mucha satisfaccion.");
            pila.push("Se eliminó al estudiante con código " + codigoEliminar);
        } else {
            System.out.println("No se encontro el estudiante.");
        }

        System.out.println("Cantidad final de estudiantes: " + lista.contar());


        System.out.println("\n[PILA DE ACCIONES]");
        System.out.println("Ultima accion registrada:");
        System.out.println(pila.top());


        System.out.println("\n[COLA DE TURNOS]");
        cola.enqueue(e1);
        cola.enqueue(e2);
        cola.enqueue(e3);

        Estudiante atendido1 = cola.dequeue();
        pila.push("Se atendio al estudiante: " + atendido1.getNombre());
        System.out.println("Atendiendo a: " + atendido1.getNombre());

        Estudiante atendido2 = cola.dequeue();
        pila.push("Se atendió al estudiante: " + atendido2.getNombre());
        System.out.println("Atendiendo a: " + atendido2.getNombre());

        System.out.println("Siguiente estudiante en espera: " + cola.front().getNombre());

        System.out.println("\n[ÁRBOL ABB]");
        int[] codigos = {50, 30, 70, 20, 40, 60, 80};
        System.out.print("Insertando codigos: ");
        for (int i = 0; i < codigos.length; i++) {
            arbol.insertar(codigos[i]);
            System.out.print(codigos[i] + (i < codigos.length - 1 ? ", " : "\n"));
        }

        System.out.println("Recorrido PreOrden:");
        arbol.preOrden();

        System.out.println("Recorrido InOrden");
        arbol.inOrden();

        System.out.println("Recorrido PostOrden:");
        arbol.postOrden();

        int codigoABB = 60;
        System.out.println("Buscar código " + codigoABB + ":");
        System.out.println(arbol.buscar(codigoABB) ? "Código encontrado." : "Código no encontrado.");

        int elimABB = 20;
        arbol.eliminar(elimABB);
        System.out.println("Eliminar código " + elimABB + ":");
        System.out.println("Código eliminado correctamente.");

        System.out.println("\n[ROTACIÓN SIMPLE IZQUIERDA]");
        ArbolABB arbRot = new ArbolABB();
        arbRot.insertar(10);
        arbRot.insertar(20);
        arbRot.insertar(30);
        System.out.println("Antes:");
        System.out.println("10 -> 20 -> 30");
        NodoABB nuevaRaizI = arbRot.rotarIzquierda(arbRot.getRaiz());
        arbRot.setRaiz(nuevaRaizI);
        System.out.println("Después:");
        System.out.println(nuevaRaizI.codigo + " como nueva raíz, "
                + nuevaRaizI.izquierdo.codigo + " a la izquierda y "
                + nuevaRaizI.derecho.codigo + " a la derecha.");


        System.out.println("\n[ROTACIÓN SIMPLE DERECHA]");
        ArbolABB arbRot2 = new ArbolABB();
        arbRot2.insertar(30);
        arbRot2.insertar(20);
        arbRot2.insertar(10);
        System.out.println("Antes:");
        System.out.println("30 -> 20 -> 10");
        NodoABB nuevaRaizD = arbRot2.rotarDerecha(arbRot2.getRaiz());
        arbRot2.setRaiz(nuevaRaizD);
        System.out.println("Después:");
        System.out.println(nuevaRaizD.codigo + " como nueva raíz, "
                + nuevaRaizD.izquierdo.codigo + " a la izquierda y "
                + nuevaRaizD.derecho.codigo + " a la derecha.");
    }
}