package logica;

import java.util.Scanner;

import dominio.Hechizo;

public class PanelAdmin {
	public static void iniciarPanelA(SistemaMagia sis, Scanner sc ) {
		int opcion = 0;
        do {
            System.out.println("\nPANEL ADMINISTRADOR");
            System.out.println("1. Agregar Mago");
            System.out.println("2. Modificar Mago");
            System.out.println("3. Eliminar Mago");
            System.out.println("4. Agregar Hechizo");
            System.out.println("5. Modificar Hechizo");
            System.out.println("6. Eliminar Hechizo");
            System.out.println("7. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            
            try {
                opcion = HerramientasConsola.leerOpcionSegura(sc); //LEEROPCIONSEGURA
                procesarOpcion(opcion, sis, sc);
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
            }
        } while (opcion != 7);
	}
	
	
	public static void procesarOpcion(int opcion, SistemaMagia sis, Scanner sc) {
		switch (opcion) {
		case 1:
            System.out.print("Ingrese el nombre del nuevo mago: ");
            String nombreMago = sc.nextLine();
            
            if (sis.agregarMago(nombreMago)) {
                System.out.println("Mago agregado con éxito!..");
                sis.guardarMagos(); //guardamos de una
            } else {
                System.out.println("Error: Ya existe un mago con ese nombre...");
            }
            break;
		case 2:
            System.out.println(sis.mostrarMagos()); // Mostramos la lista
            System.out.print("Ingrese el número del mago a modificar: ");
            int indexMod = HerramientasConsola.leerOpcionSegura(sc) - 1;
            
            System.out.println("¿Qué quiere modificar?\n"
            		+ "1) Nombre\n"
            		+ "2) Aprender hechizo\n"
            		+ "3) Olvidar hechizo");
            int opcionModificar = HerramientasConsola.leerOpcionSegura(sc);
            switch (opcionModificar) {
            
            case 1:
            	System.out.print("Ingrese el nuevo nombre del mago: ");
                String nuevoNom = sc.nextLine();
                
                if (sis.modificarMago(1, indexMod, nuevoNom)) {
                    System.out.println("Mago modificado con éxito!..");
                    sis.guardarMagos();
                } else {
                    System.out.println("Error: Número inválido o el nombre ya está en uso..");
                }
                break;
            case 2:
            	System.out.println(sis.mostrarHechizos() +
            			"\nIngrese el nombre del hechizo a aprender: ");
            	String nuevoHechizo = sc.nextLine();
            	
            	
            	if (sis.modificarMago(2, indexMod, nuevoHechizo)) {
            		System.out.println("Se ha aprendido con éxito el nuevo hechizo");
            		sis.guardarMagos();
            	} else { 
            		System.out.println("Error: No se pudo aprender el hechizo");
            		}
            	break;
            case 3:
            	System.out.println(sis.mostrarHechizosMago(indexMod) +                                 	//mostrar repertorio .getRepertorio mejor
            			"\nIngrese el nombre del hechizo a olvidar: ");
            	String hechizoOlvidable = sc.nextLine();
            	if (sis.modificarMago(3, indexMod, hechizoOlvidable)) {
            		System.out.println("Hechizo olvidado");
            		sis.guardarMagos();
            	} else {
            		System.out.println("Error: No se pudo olvidar el hechizo");
            	}
            	break;
            default:
            	System.out.println("Opción no válida...");
            }
            
            
			break;
		case 3:
			System.out.println(sis.mostrarMagos());
            System.out.print("Ingrese el número del mago a eliminar: ");
            int indexEliminar = HerramientasConsola.leerOpcionSegura(sc) - 1;
            
            if (sis.eliminarMago(indexEliminar)) {
                System.out.println("Mago eliminado con éxito!..");
                sis.guardarMagos();
            } else {
                System.out.println("Error Numero de mago inválido");
            }
			break;
		case 4:
			System.out.print("Nombre del hechizo: ");
            String nom = sc.nextLine();
            System.out.print("Tipo (Fuego, Tierra, Planta, Agua), con su mayuscula respectiva: ");
            String tipo = sc.nextLine();
            System.out.print("Daño base: ");
            int dano = HerramientasConsola.leerOpcionSegura(sc);
            
            String lineaConstruida = nom + ";" + tipo + ";" + dano;
            
            //Pedimos los datos extras dependiendo del tipo elegido.. pudo ser un switch case esto tmb pero x
            if (tipo.equalsIgnoreCase("Fuego")) {
                System.out.print("Duración de la quemadura: ");
                int duracion = HerramientasConsola.leerOpcionSegura(sc);
                lineaConstruida += ";" + duracion;
            } else if (tipo.equalsIgnoreCase("Tierra")) {
                System.out.print("Mejora de defensa: ");
                int defensa = HerramientasConsola.leerOpcionSegura(sc);
                lineaConstruida += ";" + defensa;
            } else if (tipo.equalsIgnoreCase("Planta")) {
                System.out.print("Duración del Stun: ");
                int stun = HerramientasConsola.leerOpcionSegura(sc);
                System.out.print("Cantidad de plantas: ");
                int plantas = HerramientasConsola.leerOpcionSegura(sc);
                lineaConstruida += ";" + stun + "," + plantas;
            } else if (tipo.equalsIgnoreCase("Agua")) {
                System.out.print("Cantidad de curación: ");
                int heal = HerramientasConsola.leerOpcionSegura(sc);
                System.out.print("Presión del agua: ");
                int presion = HerramientasConsola.leerOpcionSegura(sc);
                lineaConstruida += ";" + heal + "," + presion;
            } else {
            	System.out.println("Ese no es un tipo válido");
            	break;
            }
            
            if (sis.agregarHechizo(lineaConstruida)) {//ocupamos metodo del sistema
                System.out.println("Hrchizo agregado!!");
                sis.guardarHechizos();
            } else {
                System.out.println("Error no se pudo agregar (datos inválidos o ya existe)");
            }
            break;
		case 5:
			System.out.println(sis.mostrarHechizos());
			System.out.print("Seleccione el índice del hechizo a modificar: ");
			int idxMod = HerramientasConsola.leerOpcionSegura(sc) - 1;

			Hechizo hMod = sis.obtenerHechizo(idxMod); //hechizo qe vamos a modificar
			if (hMod == null) {
				System.out.println("Error indice inválido.");
				break;
			}

			System.out.println("Modificando hechizo de tipo " + hMod.getTipo().toUpperCase() + "...");
			System.out.print("Ingrese el nuevo nombre (o el mismo para mantenerlo): ");
			String nNombre = sc.nextLine();
			System.out.print("Ingrese el nuevo daño base: ");
			int nDano = HerramientasConsola.leerOpcionSegura(sc);

			int ext1 = 0, ext2 = 0;
			if (hMod.getTipo().equalsIgnoreCase("Fuego")) {
				System.out.print("Nueva duración de quemadura: ");
				ext1 = HerramientasConsola.leerOpcionSegura(sc);
			} else if (hMod.getTipo().equalsIgnoreCase("Tierra")) {
				System.out.print("Nueva mejora de defensa: ");
				ext1 = HerramientasConsola.leerOpcionSegura(sc);
			} else if (hMod.getTipo().equalsIgnoreCase("Planta")) {
				System.out.print("Nueva duración del Stun: ");
				ext1 = HerramientasConsola.leerOpcionSegura(sc);
				System.out.print("Nueva cantidad de plantas: ");
				ext2 = HerramientasConsola.leerOpcionSegura(sc);
			} else if (hMod.getTipo().equalsIgnoreCase("Agua")) {
				System.out.print("Nueva cantidad de curación: ");
				ext1 = HerramientasConsola.leerOpcionSegura(sc);
				System.out.print("Nueva presión de agua: ");
				ext2 = HerramientasConsola.leerOpcionSegura(sc);
			}

			if (sis.modificarHechizo(idxMod, nNombre, nDano, ext1, ext2)) {
				System.out.println("Hechizo modificado con éxito en el catálogo y en todos los magos!!!!");
				sis.guardarHechizos();
				sis.guardarMagos(); //guardar magos porque su puntaje total cambio
			} else {
				System.out.println("Error al modificar");
			}
			break;
		case 6:
			System.out.println(sis.mostrarHechizos());
            System.out.print("Seleccione el índice del hechizo a eliminar: ");
            int idxEliminar = HerramientasConsola.leerOpcionSegura(sc) - 1;
            
			if (sis.eliminarHechizo(idxEliminar)) {
                System.out.println("Hechizo eliminado con éxito..");
                sis.guardarHechizos();
            } else {
                System.out.println("Error indice inválido");
            }
			sis.guardarHechizos();
			break;
		case 7:
			System.out.println("\nVolviendo al menú principal...\n");
			break;
		default:
			System.out.println("Error, opcion no valida...");
			break;
		}
	}
	
	
}
