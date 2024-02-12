package maze;

import java.util.ArrayList;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.nio.file.Files;
import java.nio.file.Paths;

import graph.Graph;
import graph.Vertex;


public class Maze implements Graph {

    private ArrayList<MazeBox> maze; //Remarque: c'est une liste de MazeBox et pas un tableau
	private int width ;
	private int length;

	//constructeur du Maze

	public Maze(final int width, final int length){ 
		this.maze = new ArrayList<MazeBox>();
		this.width = width; 						//largeur= absisse x
		this.length = length; 						//hauteur ordonnée y 
		this.maze = new ArrayList<MazeBox>();
		for (int y=0 ; y<length ; y++) { 			//variation des ordonnées
			for (int x = 0 ; x<width ; x++) { 		//variation des abscisses
				MazeBox mazebox = new EmptyBox(x,y);
				maze.add(mazebox);
			}
		}
	}	

	//getters et setters
	public final int getWidth() {
		return width;
	}
	public final void setWidth(final int width) {
		this.width = width;
	}
	public final int getLength() {
		return length;
	}
	public final void setLength(final int length) {
		this.length = length;
	}

	////////////////////////////////


	// on récupère la longueur et la largeur du labyrinthe avec les coordonnées de la dernière Mazebox
	private final void  dimension(final int w, final int l){
		width = w;
		length= l;
	}

	//on récupère les données d'une case à partir de ses coordonnées

	public final MazeBox getMazeBox(final int x, final int y) {
		return maze.get(y*width +x) ;
	}

	// pour une raison qui m'est inconnue on ne peut pas utiliser les fonctions set et add à l'extérieur de cette classe
	public final void set2(final int k, final MazeBox mazeBox) {
		maze.set(k, mazeBox);
    }
	
	public final void add2(final MazeBox mazeBox) {
		maze.add(mazeBox);
	}

	/**
	 * Si les deux labyrinthes sont identiques, on renvoie true, (sert pour l'interface graphique )
	 * @param maze2 le labyrinthe à comparer avec le labyrinthe courant
	 * @return true si les deux labyrinthes sont identiques
	 */
	public final boolean chgmtMaze(final Maze maze2){
		for (int y=0; y<length; y++){
			for (int x=0; x<width ;x++){
				if (maze2.getMazeBox(x,y) != getMazeBox(x,y)){
					return false;
				}
			}
		}
		return true;
	}


	/**
	 * Récupère la box contenant les données de la case départ sous forme du vertex, et renvoie une erreur si le nombre de case valide est pas bon
	 * @return le vertex de la case départ 
	 * @throws DepartureArrivalException 
	 * @throws DepartureArrivalException2
	 */
	public final Vertex getDeparture() throws DepartureArrivalException, DepartureArrivalException2 {
		int d =0;
		Vertex departure = null;
		for (int y=0 ; y<length ; y++) {
			for (int x = 0 ; x<width ; x++) {
				Vertex mazebox = maze.get(y*width +x) ;
				if (mazebox.getLabel() == "D") {
					d+=1;
					departure = mazebox ; }
			}
		}
		if (d>1) {throw new DepartureArrivalException2(" cases departs");}
		if (departure != null ){return departure; }
			
			
		throw new DepartureArrivalException(" depart");
	}
	
	/**
	 * Récupère la box contenant les données de la case arrivée sous forme du vertex, et renvoie une erreur si le nombre de case valide est pas bon
	 * @return le vertex de la case arrivée
	 * @throws DepartureArrivalException
	 */
	public final Vertex getArrival() throws DepartureArrivalException, DepartureArrivalException2 {
		int d=0;
		Vertex arrive = null;
		for (int y=0 ; y<length ; y++) {
			for (int x = 0 ; x<width ; x++) {
				Vertex mazebox = maze.get(y*width +x) ;
				if (mazebox.getLabel() == "A") {
					d+=1;
					arrive = mazebox ; }
			}	
		}
		if (d>1) {throw new DepartureArrivalException2(" cases arrivées");}
		if (arrive != null ){return arrive; }	
		
		throw new DepartureArrivalException(" arrivée");
	}


	/**
	 * Renvoie une chaîne de caractères représentant le labyrinthe 
	 * @return une chaîne de caractères représentant le labyrinthe
	 */
	public final String toString() {

		String s = "";
		for (int y=0 ; y<length ; y++) {
			for (int x = 0 ; x<width ; x++) {
				s += maze.get(y*width +x).getLabel();
			}
			s += "\n";
		}
		return s;
	}
	/**
	 * print une chaîne de caractères représentant le labyrinthe et le nombre de cases
	 * @return void
	 */
	public final void toString2(){
		int i=0;
		for (MazeBox box : maze){
			System.out.println(box.toString());
			i++;
		}
		System.out.println(i);
	}

	/**
	 * renvoie une liste de vertex au lieu de MazeBox
	 * @return le labyrinthe en liste de vertex
	 */
    public final ArrayList<Vertex> getAllVertexes(){
    	ArrayList<Vertex> maze2 = new ArrayList<Vertex>() ;
    	for (MazeBox box : maze) {
    		Vertex vertex = (Vertex) box;
    		maze2.add(vertex); }
    	return maze2;
    	
    }
	/**
	 * @param i le vertex de départ
	 * @param j le vertex d'arrivée
	 * @return 1 si j voisin de i 0 sinon
	 */
	public final int getPoids(final Vertex i, final Vertex j){
    	List<Vertex> success= succVertex(i);
		if (success.contains(j)) {return 1;}
		else{return 0;}
	}

    /**
	 * Renvoie la liste des successeurs d'un vertex de la manière suivante, les deux points de même ordonnées avec une abscisse de +-1 sont sucesseurs
	 * les deux points de même abscisse avec une ordonnée de +-1 sont successeurs
	 * les deux points d'ordonnées +-1 et d'abscisses -1 sont succeseurs si y est pair et +1 si y est impair
	 * on teste aussi si le vertex est un wall dans ce cas il n'est pas successeur
	 * @param sommet le vertex dont on veut les successeurs
	 * @param x l'abscisse du vertex
	 * @param y l'ordonnée du vertex
	 * @return la liste des successeurs du vertex
	 * 
	 */
    public final List<Vertex> succVertex(final Vertex sommet){
		ArrayList<Vertex> successor = new ArrayList<Vertex>();
		
		int x = sommet.getX();
		int y = sommet.getY();
		for (MazeBox box : maze) {

			Vertex vertex =(Vertex) box;
			if ((vertex.getY() == y) && (vertex.getX() == x-1 || vertex.getX() == x+1) && (vertex.getPassage())){
				successor.add(vertex);}
		
			if ((vertex.getX() == x) && (vertex.getY() == y-1 || vertex.getY() == y+1) && (vertex.getPassage())){
				successor.add(vertex);}
			
			if ((vertex.getX() == x-1) && (vertex.getY() == y-1 || vertex.getY() == y+1) && (y%2==0)&& (vertex.getPassage())){
				successor.add(vertex);}
		
			if ((vertex.getX() == x+1) && (vertex.getY() == y-1 || vertex.getY() == y+1)&& (y%2==1) && (vertex.getPassage())){
				successor.add(vertex);}
					
		}    	
    	return successor;
    }
// pas très optimal car on regarde tout les éléments du tableaux mais on se stop quand on en a 6 et cette écriture permet de ne pas avoir à gérer les bords


	/**
	 * crée un maze vide et le complète en fonction des valeurs du fichier texte
	 * 
	 * @param fileName le nom du fichier texte importé
	 * @param lines la liste des lignes du fichier texte
	 * @param nbcol la longueur de la première ligne
	 * @param nblig le nombre de ligne
	 * @param line la ligne en cours de lecture
	 * @throws MazeReadingException si le fichier texte n'est pas conforme
	 */
    public final void initFromTextFile(final String fileName) throws MazeReadingException {
		try{
			
			List <String> lines= Files.readAllLines(Paths.get(fileName));
			int nbcol = lines.get(0).length(); //longueur de la première ligne
			int nblig =lines.size(); //on fait varier le numéro de ligne donc l'ordonnée 
			for (int y=0; y<nblig ; y++) {
				String line = lines.get(y);
				if(line.length()!= nbcol) {
					throw new MazeReadingException( fileName, y , " ligne " + line+ " trop longue ou trop courte");
				}
				/*  */
				for (int x=0 ; x<nbcol ; x++) {
					switch (line.charAt(x)) {
						case('E'): 
							maze.add(new EmptyBox(x,y)); 
							break;
						case('D'): 
							maze.add(new DepartureBox(x,y)); 
							break;
						case('W'): 
							maze.add(new WallBox(x,y)); 
							break;
						case('A'): 
							maze.add(new ArrivalBox(x,y)); 
							break;
						default : {throw new MazeReadingException( fileName, x, "le caractere " + '"'+ y + '"' + "n'est pas valide à la ligne " + line);}
					}
				}
				dimension(nbcol, nblig); // on met à jour la largeur et la longueur de maze
			}		
		} catch(FileNotFoundException e){
			e.printStackTrace();
		} catch(IOException e){
			e.printStackTrace();	
		} 
		
    }
	/**
	 * @param fileName le nom du fichier qui sera sauvegardé
	 * @param pw le PrintWriter qui permet d'écrire dans le fichier
	 */
	public final void saveToTextFile(final String fileName) {
		try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))){
			for( int y =0; y< length  ; y++ ){
				for (int x=0; x< width ; x++) {
					pw.print(maze.get(x+width*y).getLabel());
				}
				pw.println("");
			}

		} 
		catch(IOException e){e.printStackTrace();}	
	}


	/**
	 * change tout les Vertex du chemin en . au lieu d'emptyBox
	 * @param fileName le nom du fichier qui sera sauvegardé
	 * @param pw le PrintWriter qui permet d'écrire dans le fichier
	 */
	public final void saveSoluce(final String fileName, final List<Vertex> chemin) {
		try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))){
			for( int y =0; y< length  ; y++ ){
				for (int x=0; x< width ; x++) {
					if(chemin.contains(maze.get(x+width*y))){
						pw.print("."); System.out.println(maze.get(x+width*y));
					}
					else {pw.print(maze.get(x+width*y).getLabel());}
		
				}
				pw.println("");
			}

		} 
		catch(IOException e){e.printStackTrace();}	
	}

}

