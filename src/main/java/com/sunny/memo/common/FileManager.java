package com.sunny.memo.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class FileManager {

	// 상수 = 전체 대문자로
	// private -> public
	public final static String FILE_UPLOAD_PATH = "C:\\Study\\6_spring_project_info\\upload\\memo";
	
	// file을 특정 디렉토리(폴더)에 저장하고,
	// 해당 파일을 접근할 수 있는 ulr경로를(String) 만들고 return
	
	public static String saveFile(int userId , MultipartFile file) {
	
		// 이미지 파일 저장하기
		if (file == null) {
			return null;
		}
		
		// 같은 파일이름 처리
		// 디렉토리(폴더) 만들어서 파일 저장
		// 로그인 사용자의 userId 를 디렉토리 이름에 포함
		// 현재 시간 정보를 디렉토리 이름에 포함
		// UNIX TIME : 1970년 1월 1일을 기준으로 흐른 시간을 milli second(1/1000) 단위로 표현한 방식
			// ex) 2_328973298
			// 사용자 아이디 + 현재 시간 정보 = 폴더 이름으로 구분
		
		// 디렉토리 이름 만들기
		String directoryName = "/" + userId + "_" + System.currentTimeMillis();
		
		// 디렉토리 생성 (폴더 생성) = 사용법
		// 전체경로를 만들어 줘야 한다.
		String directoryPath = FILE_UPLOAD_PATH + directoryName;
		
		File directory = new File(directoryPath);
		
		// directory.mkdir();
		// 생성 성공 true return, 생성 실패 false return
		
		if (!directory.mkdir()) {
			// 디렉토리 생성 실패
			return null;	
		}
		
		// 파일 저장 = 전체경로 + / +파일이름
		String filePath = directoryPath + "/" + file.getOriginalFilename();
		
		try {
			byte[] bytes = file.getBytes();
			
			// path라는 형태의 객체로 만들어서 인자로 넣어줘야 된다
			Path path = Paths.get(filePath);
			Files.write(path , bytes);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
			// 파일 저장 실패
			return null;
		}
		
		// 파일 저장 목적 성공
		// 클라이언트에서 접근 가능한 url 규칙을 만들고 해당 문자열을 리턴
		// 파일 경로 : C:\\Study\\6_spring_project_info\\upload\\memo/2_328973298/test.png
		// 경로 규칙 : /images/2_328973298/test.png
		
		return "/images" + directoryName + "/" + file.getOriginalFilename();
	}
	
	// 이미지 파일 삭제하기 + 마지막에 static으로 바꿔줌
	public static boolean removeFiles(String filePath) {  //  /images/2_328973298/test.png
		
		// 이미지파일이 없는 것 삭제할 떄 에러나서 null처리
		if(filePath == null) {
			return false;
		}
		
		// 이미지 파일 경로에서 /images 제거 후
		// filePath.replace("/images", "");  //  /2_328973298/test.png
		
		// upload 경로를 이어 붙여 준다.
		String fullFilePath = FILE_UPLOAD_PATH + filePath.replace("/images", ""); 
		
		// 사용법
		Path path = Paths.get(fullFilePath);
		
		// 삭제할 파일이 존재하는지 확인하고 삭제
		// Files.exists(path) -> true false return
		if (Files.exists(path)) {
		
			try {
				Files.delete(path);
			} catch (IOException e) {
				e.printStackTrace();
				
				return false;
			}
		}
		
		// 저장된 파일의 폴더 = 디렉토리도 삭제
		Path dirPath = path.getParent(); // 새로운 경로를 리턴 = 디렉토리 경로
		
		// 디렉토리가 존재하는지 확인하고 삭제
		if (Files.exists(dirPath)) {
			try {
				Files.delete(dirPath);
			} catch (IOException e) {
				e.printStackTrace();
				
				return false;
			}
		}
		
		return true;
	}
	
}
