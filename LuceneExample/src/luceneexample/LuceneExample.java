/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luceneexample;

/**
 *
 * @author TienDV
 */
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class LuceneExample {
    
   String indexDir = "D:\\Codes\\Lucene\\textscreen\\index";
   String dataDir = "D:\\Codes\\Lucene\\textscreen\\text";
   Indexer indexer;
   Searcher searcher;

   public static void main(String[] args) throws IOException, ParseException {
      LuceneExample tester;
      tester = new LuceneExample();
    //  String indexDir = "D:\\Codes\\Lucene\\index";
     // tester.searchInDirectory(indexDir, "a person holding a knife");
      try {
         tester = new LuceneExample();
         tester.createIndex();
         tester.search("news");
      } catch (IOException e) {
         e.printStackTrace();
      } catch (ParseException e) {
         e.printStackTrace();
      }
   }

   private void createIndex() throws IOException{
      indexer = new Indexer(indexDir);
      int numIndexed;
      long startTime = System.currentTimeMillis();	
      numIndexed = indexer.createIndex(dataDir, new TextFileFilter());
      long endTime = System.currentTimeMillis();
      indexer.close();
      System.out.println(numIndexed+" File indexed, time taken: "
         +(endTime-startTime)+" ms");		
   }

   private void search(String searchQuery) throws IOException, ParseException{
      searcher = new Searcher(indexDir);
      long startTime = System.currentTimeMillis();
      TopDocs hits = searcher.search(searchQuery);
      long endTime = System.currentTimeMillis();
   
      System.out.println(hits.totalHits +
         " documents found. Time :" + (endTime - startTime));
      for(ScoreDoc scoreDoc : hits.scoreDocs) {
         Document doc = searcher.getDocument(scoreDoc);
            System.out.println("File: "
            + doc.get(LuceneConstants.FILE_NAME));
      }
      searcher.close();
   }
   private void searchInDirectory (String indexDirectory, String queryString) throws IOException, ParseException{
       
       System.out.println("Searching for '" + queryString + "'");
       File pathIndexDir = new File(indexDirectory);
       Directory index = FSDirectory.open(pathIndexDir);
       IndexReader reader = IndexReader.open(index);
       IndexSearcher searcher = new IndexSearcher(reader);
       QueryParser queryParser = new QueryParser(Version.LUCENE_36,
         LuceneConstants.CONTENTS,
         new StandardAnalyzer(Version.LUCENE_36));
       Query query = queryParser.parse(queryString);
       TopDocs tops= searcher.search(query, 30);
       ScoreDoc[] scoreDoc = tops.scoreDocs;
        System.out.println(scoreDoc.length); 
        for (ScoreDoc score : scoreDoc){
    //    System.out.println("DOC " + score.doc + " SCORE " + score.score);
         Document doc = searcher.doc(score.doc);
            System.out.println("File: "
            + doc.get(LuceneConstants.FILE_NAME));
        searcher.close();
}
   }
    
}
