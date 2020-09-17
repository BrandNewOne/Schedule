package com.example.calpjt1;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class AboutFile {
    private Context context;

    public AboutFile(Context context){
        this.context=context;
    }

    public List<String> FileList(String folderName) {
        String path = context.getFilesDir().toString()+"/"+folderName;
        try {
            File directory = new File(path);
            File[] files = directory.listFiles();

            List<String> filesNameList = new ArrayList<>();

            for (int i = 0; i < files.length; i++) {
                filesNameList.add(files[i].getName());
            }
            return filesNameList;
        }catch (Exception e){
            return null;
        }

    }

    //텍스트내용을 경로의 텍스트 파일에 쓰기
    public void writeFile(String folderName, String fileName, String Contents){
        String path = context.getFilesDir().toString();
        try{
            File dir = new File (path+"/"+folderName);
            //디렉토리 폴더가 없으면 생성함
            if(!dir.exists()){
                dir.mkdir();
            }
            //파일 output stream 생성
            FileOutputStream fos = new FileOutputStream(path+"/"+folderName+"/"+fileName,true);
            //파일쓰기
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
            writer.write(Contents);
            writer.flush();

            writer.close();
            fos.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    //파일 읽기

    public String readFile(String fileName) {
        String a = null;
        String path = context.getFilesDir().toString();
// 파일 생성
        String line = null; // 한줄씩 읽기
        File saveFile = new File(path+"/day"); // 저장 경로
// 폴더 생성
        if(!saveFile.exists()){ // 폴더 없을 경우
            return a="그런 폴더 없지롱~!";
        }
        try {
            BufferedReader buf = new BufferedReader(new FileReader(saveFile+"/"+fileName));
            while((line=buf.readLine())!=null){
                a=line;
            }
            buf.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return a;

    }
}
