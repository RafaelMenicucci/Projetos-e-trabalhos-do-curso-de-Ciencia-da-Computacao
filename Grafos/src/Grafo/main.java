/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException; 
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import static jdk.nashorn.tools.ShellFunctions.input;

/**
 *
 * @author rafa_
 */
public class main {
    
    public static void main(String[] args) throws IOException{
        String rep = null;
        String f = null;
        String csvorigem = null;
        String csvdestino = null;
        int x =0;
        int vertInicial = 0;
        int origem = 0;
        int destino = 0;
        int i=0;
        int tamanho = args.length;

        for(i=0;i<tamanho-1;i++){
            if("-rep".equals(args[i])){
                rep = args[i+1];
            }else if("-f".equals(args[i])){
                f = args[i+1];
            }else if("-csvorigem".equals(args[i])){
                csvorigem = args[i+1];
            }else if("-csvdestino".equals(args[i])){
                csvdestino = args[i+1];
            }else if("-grafo".equals(args[i])){
                x = Integer.parseInt(args[i+1]);
            }else if("-verticeinicial".equals(args[i])){
                vertInicial = Integer.parseInt(args[i+1]);
            }else if("-origem".equals(args[i])){
                origem = Integer.parseInt(args[i+1]);
            }else if("-destino".equals(args[i])){
                destino = Integer.parseInt(args[i+1]);
            }
        }
      
        /*Scanner scanner = new Scanner(System.in);
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.println("Digite 1 para Matriz adjacencia e 2 para lista adjacencia: ");
        int x = scanner.nextInt();
        
        System.out.println("Digite o nome do arquivo");
        String y = input.readLine();*/
        Leitura L = new Leitura();
        LinkedList<Float> desenho = new LinkedList();

        
        if(x==1){
            Grafoabstract MA = new Grafo_MA(L.Ler(csvorigem,rep));
            addAresta add = new addAresta();
            MA = add.add(MA,csvorigem,rep);
            desenho = add.getDesenho();
            int []descoberto= null;
            int []finalizado = null;
            if("DFS".equals(rep)){
                if("r".equals(f)){                   
                    DFS dfs = new DFS(MA);
                    dfs.DFSgrafo();
                    descoberto = dfs.getD();
                    finalizado = dfs.getF();
                }else if("i".equals(f)){
                    DFSIterativo dfsI = new DFSIterativo(MA);
                    dfsI.DFSgrafo();
                    descoberto = dfsI.getD();
                    finalizado = dfsI.getF();
                }  
            }else if("BFS".equals(rep)){
                BFS bfs = new BFS(MA, vertInicial);
                bfs.BFSgrafo();
                descoberto = bfs.getD();
                finalizado = bfs.getPi();
            }else if("kruskal".equals(rep)){
                AGM agm = new AGM(MA, rep,0);
                agm.AGMGrafo();
                descoberto = agm.getY();
                finalizado = agm.getX();
            }else if("prim".equals(rep)){
                AGM agm = new AGM(MA, rep,vertInicial);
                agm.AGMGrafo();
                descoberto = agm.getY();
                finalizado = agm.getX();
                Desenho d = new Desenho(desenho,descoberto,finalizado);
            }else if("Bellman-Ford".equals(rep)){
                CaminhoMinimo caminho = new CaminhoMinimo(MA, rep,vertInicial);
                caminho.CaminhoMinimoGrafo();
                descoberto = caminho.getvertice();
                finalizado = caminho.getpi();
                Desenho d = new Desenho(desenho,descoberto,finalizado);
            }else if("Dijkstra".equals(rep)){
                CaminhoMinimo caminho = new CaminhoMinimo(MA, rep,vertInicial);
                caminho.CaminhoMinimoGrafo2();
                descoberto = caminho.getvertice();
                finalizado = caminho.getpi();
                //Desenho d = new Desenho(desenho,descoberto,finalizado);
            }
            //
            //
            Escrever E = new Escrever(rep, descoberto, finalizado,MA);
            E.EscreverGrafo(csvdestino);
        }else{
            Grafoabstract LA = new Grafo_LA(L.Ler(csvorigem,rep));
            addAresta add = new addAresta();
            LA = add.add(LA,csvorigem,rep);
            desenho = add.getDesenho();
            int []descoberto = null;
            int []finalizado = null;
            if("DFS".equals(rep)){
                if("r".equals(f)){                   
                    DFS dfs = new DFS(LA);
                    dfs.DFSgrafo();
                    descoberto = dfs.getD();
                    finalizado = dfs.getF();
                }else if("i".equals(f)){
                    DFSIterativo dfsI = new DFSIterativo(LA);
                    dfsI.DFSgrafo();
                    descoberto = dfsI.getD();
                    finalizado = dfsI.getF();
                }
            }else if("BFS".equals(rep)){
                BFS bfs = new BFS(LA, vertInicial);
                bfs.BFSgrafo();
                descoberto = bfs.getD();
                finalizado = bfs.getPi();
            }else if("kruskal".equals(rep)){
                AGM agm = new AGM(LA, rep,0);
                agm.AGMGrafo();
                //descoberto = bfs.getD();
                //finalizado = bfs.getPi();
            }else if("prim".equals(rep)){
                AGM agm = new AGM(LA, rep, vertInicial);
                agm.AGMGrafo();
                descoberto = agm.getY();
                finalizado = agm.getX();
                Desenho d = new Desenho(desenho,descoberto,finalizado);
            }else if("Bellman-Ford".equals(rep)){
                CaminhoMinimo caminho = new CaminhoMinimo(LA, rep,vertInicial);
                caminho.CaminhoMinimoGrafo();
                descoberto = caminho.getvertice();
                finalizado = caminho.getpi();
            }else if("Dijkstra".equals(rep)){
                CaminhoMinimo caminho = new CaminhoMinimo(LA, rep,vertInicial);
                caminho.CaminhoMinimoGrafo2();
                descoberto = caminho.getvertice();
                finalizado = caminho.getpi();
            }
            Escrever E = new Escrever(rep, descoberto, finalizado,LA);
            E.EscreverGrafo(csvdestino);
        }
        
        
       //-------------------------
       //-------------------------
       //-------------------------
       //-------------------------
       //-------------------------
       //-------------------------
       //PARA GRAFO MATRIZ ADJACENCIA
       //-------------------------
       //-------------------------
       //-------------------------
       //-------------------------
       //-------------------------
        /*int nVertices = 0; 
        int maior = 0;
    
        File arquivoCSV = new File("C:\\Users\\rafa_\\OneDrive\\Documentos\\NetBeansProjects\\tp1_file_005.csv");
        
        BufferedReader conteudoCSV = null;
        String linha = "";
        String csvSeparadorCampo = ",";
        try {
            
            conteudoCSV = new BufferedReader(new FileReader(arquivoCSV));
       
            conteudoCSV.readLine();
            
            while((linha = conteudoCSV.readLine()) != null) { 
                if(linha.equals("")){
                    break;
                }
                
                String [] grafo = linha.split(csvSeparadorCampo); 
                
                int i = Integer.parseInt(grafo[0]);
                int j = Integer.parseInt(grafo[1]);
                
                
                if(i > j) {
                    maior = i;
                } else {
                    maior = j;
                }
                
                if(maior > nVertices) {
                    nVertices = maior;
                }
                
            }
            
            
        } catch(FileNotFoundException e){
                
        }
        
        Grafo_MA MA = new Grafo_MA(nVertices+1);
        
        BufferedReader addarestaCSV = null;
        try {
            
            addarestaCSV = new BufferedReader(new FileReader(arquivoCSV));
       
            addarestaCSV.readLine();
            
            while((linha = addarestaCSV.readLine()) != null) { 
                if(linha.equals("")){
                    break;
                }
               
                String [] grafo = linha.split(csvSeparadorCampo); 
                
                
                int i = Integer.parseInt(grafo[0]);
                int j = Integer.parseInt(grafo[1]);
                
                MA.addAresta(i, j, 1);
            }
            
        } catch(FileNotFoundException e){
                
        }
        MA.DFS();

        
        //-------------------------
       //-------------------------
       //-------------------------
       //-------------------------
       //-------------------------
       //-------------------------
       //PARA GRAFO LISTA ADJACENCIA
       //-------------------------
       //-------------------------
       //-------------------------
       //-------------------------
       //-------------------------
       
        int nVerticesLA = 0; 
        int maiorLA = 0;
    
        File arquivoLACSV = new File("C:\\Users\\rafa_\\OneDrive\\Documentos\\NetBeansProjects\\tp1_file_005.csv");
        
        BufferedReader conteudoLACSV = null;
        try {
            
            conteudoLACSV = new BufferedReader(new FileReader(arquivoLACSV));
       
            conteudoLACSV.readLine();
            
            while((linha = conteudoLACSV.readLine()) != null) { 
                if(linha.equals("")){
                    break;
                }
                
                String [] grafo = linha.split(csvSeparadorCampo); 
                
                int i = Integer.parseInt(grafo[0]);
                int j = Integer.parseInt(grafo[1]);
                
                
                if(i > j) {
                    maiorLA = i;
                } else {
                    maiorLA = j;
                }
                
                if(maiorLA > nVerticesLA) {
                    nVerticesLA = maior;
                }
                
            }
            
            
        } catch(FileNotFoundException e){
                
        }
        
        Grafo_LA LA = new Grafo_LA(nVerticesLA+1);
        
        BufferedReader addarestaLACSV = null;
        try {
            
            addarestaLACSV = new BufferedReader(new FileReader(arquivoLACSV));
       
            addarestaLACSV.readLine();
            
            while((linha = addarestaLACSV.readLine()) != null) { 
                if(linha.equals("")){
                    break;
                }
               
                String [] grafo = linha.split(csvSeparadorCampo); 
                
                
                int i = Integer.parseInt(grafo[0]);
                int j = Integer.parseInt(grafo[1]);
                
                LA.addAresta(i, j, 1);
            }
            
        } catch(FileNotFoundException e){
                
        }
        LA.DFS();
     */  
    }
}
