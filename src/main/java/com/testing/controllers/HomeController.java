package com.testing.controllers;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.html2pdf.HtmlConverter;
import com.testing.entity.Person;
import com.testing.perRepo.PersonRepo;

@RestController
public class HomeController {
	
	private String htmlData;
	
	
	@Autowired
	ResourceLoader resourceLoader;
	
	
	@RequestMapping("/pdf")
	public ResponseEntity<?> home() throws IOException {
		
		  
		  String html="<!doctype html>\r\n"
		  		+ "<html lang=\"en\">\r\n"
		  		+ "<head>\r\n"
		  		+ "    <title>SpringHow html to pdf</title>\r\n"
		  		+ "    <link rel=\"stylesheet\" href=\"style.css\">\r\n"
		  		+ "</head>\r\n"
		  		+ "<body>\r\n"
		  		+ "     <div>\r\n"
		  		+ "        <p >Lorum ipsum some text before image. Lorum ipsum some text before image. Lorum ipsum some text before image. Lorum ipsum some text before image. Lorum ipsum some text before image. Lorum ipsum some text before image. Lorum ipsum some text before image. Lorum ipsum some text before image. </p>\r\n"
		  		+ "        <img src=\"photo.jpg\" alt=\"Orange\">\r\n"
		  		+ "        <p >Lorum ipsum some text after image. Lorum ipsum some text after image. Lorum ipsum some text after image. Lorum ipsum some text after image. Lorum ipsum some text after image. Lorum ipsum some text after image. Lorum ipsum some text after image. Lorum ipsum some text after image. Lorum ipsum some text after image.</p>\r\n"
		  		+ "        <table>\r\n"
		  		+ "            <tr><th>Product</th><th>Quantity</th><th>Price</th><th>Total</th></tr>\r\n"
		  		+ "            <tr><td>Jeans</td><td>2</td><td>10.99</td><td>20.98</td></tr>\r\n"
		  		+ "            <tr><td>Shirt</td><td>2</td><td>7.99</td><td>14.98</td></tr>\r\n"
		  		+ "        </table>\r\n"
		  		+ "     </div>\r\n"
		  		+ "</body>\r\n"
		  		+ "</html>";
		  
		  ByteArrayOutputStream target = new ByteArrayOutputStream();
		  
		  
		 
		  
		  HtmlConverter.convertToPdf(html, target);
		
		  return ResponseEntity.ok()
		            .contentType(MediaType.APPLICATION_PDF)
		            .body(target.toByteArray());
		  
	//	return "JUST FOR TESTING";
		
	}
	
	
	
	
	@RequestMapping("/downloadPDFWithResource")
	public ResponseEntity<?> htmlReadData(HttpServletResponse response) throws IOException
	{
		
		
		Resource resource = new ClassPathResource("/");
		String path = resource.getFile().getAbsolutePath();
		// System.out.println(path);

		Resource resource2 = resourceLoader.getResource("/WEB-INF/jsp/index.jsp");
		String absolutePath = resource2.getFile().getAbsolutePath();
		 System.out.println(absolutePath);
		System.out.println("Path Fetch Success and attach a file Name");

		System.out.println("*************************************************");
		
	
		
		try {

			byte[] copyToByteArray = FileCopyUtils.copyToByteArray(resource2.getInputStream());
			htmlData = new String(copyToByteArray, StandardCharsets.UTF_8);
			// System.out.println(htmlData);

			System.out.println("**********************************************");
			
			// Creating Map and Putting Value
			Map<String, String> map = new LinkedHashMap<>();

			map.put("name", "Rahul jaiswal");
			map.put("mobile", "9845100000");
			map.put("email", "rahul@gmail.com");
			map.put("city", "DELHI");
			

			// System.out.println(map);
			System.out.println("############################################");

			System.out.println(htmlData);
			for (String key : map.keySet()) {
				String value = map.get(key);

				System.out.println(value);

				value = value == null ? "null" : value;
				htmlData = htmlData.replace("{{" + key + "}}", value);

			}
			 System.out.println("****PERPARE TO FLY******");

			 
			 ByteArrayOutputStream target = new ByteArrayOutputStream();
			  
			  HtmlConverter.convertToPdf(htmlData, target);
			
//			  return ResponseEntity.ok()
//			            .contentType(MediaType.APPLICATION_PDF)
//			            .body(target.toByteArray());
			  
			  
			  
			  
		 ServletOutputStream outputStream = response.getOutputStream();
			  
			  response.setContentType("APPLICATION/OCTET-STREAM");   
			  response.setHeader("Content-Disposition","attachment; filename=\"" + "NewTEST.pdf" + "\"");   
			  
			  outputStream.write(target.toByteArray());
			  
			  
			  return null;
			
		} catch (Exception e) {
			e.printStackTrace();
			
			return null;

		}
		
	}
	
	
	
	
	@Autowired
	private PersonRepo personRepo;
	
	
	@RequestMapping("/htmlData")
	public ResponseEntity<?> HTMLDATA(HttpServletResponse response) throws IOException
	{
		StringBuilder builder=new StringBuilder();
		
		String newHTML=null;
		try {
			
			Resource resource2 = resourceLoader.getResource("/WEB-INF/jsp/demo1.html");
			
			byte[] copyToByteArray = FileCopyUtils.copyToByteArray(resource2.getInputStream());
			 newHTML = new String(copyToByteArray, StandardCharsets.UTF_8);
			 
			 builder.append(newHTML);
		
			 	Resource resource3 = resourceLoader.getResource("/WEB-INF/jsp/demo2.html");
				byte[] copyToByteArray3 = FileCopyUtils.copyToByteArray(resource3.getInputStream());
				String newHTML2 = new String(copyToByteArray3, StandardCharsets.UTF_8);
				
								
			
				Map<String, String> map = new LinkedHashMap<>();
				map.put("name", "Rahul jaiswal");
				map.put("mobile", "9845100000");
				map.put("email", "rahul@gmail.com");
				map.put("city", "DELHI");
				
				for (String key : map.keySet()) {
					String value = map.get(key);
					value = value == null ? "null" : value;
					newHTML2 = newHTML2.replace("{{" + key + "}}", value);
				}
			 
			
			 
			 
			 //RESOURCE # DEMO_3
			 Resource resource4 = resourceLoader.getResource("/WEB-INF/jsp/demo3.html");
				byte[] copyToByteArray4 = FileCopyUtils.copyToByteArray(resource4.getInputStream());
				String newHTML4 = new String(copyToByteArray4, StandardCharsets.UTF_8);
				
				builder.append(newHTML4);
			 
			
				//DOWNLOADING AND TRANSMITION CODE TO PDF
			 System.out.println("******PERPARE TO FLY******");
			// String finalHTMl=newHTML+newHTML2+newHTML4;
			 
			 ByteArrayOutputStream target = new ByteArrayOutputStream();
			  
			  HtmlConverter.convertToPdf(builder.toString(), target);
			  ServletOutputStream outputStream = response.getOutputStream();
			  
			  response.setContentType("APPLICATION/OCTET-STREAM");   
			  response.setHeader("Content-Disposition","attachment; filename=\"" + "NewTEST1.pdf" + "\"");   
			  
			  outputStream.write(target.toByteArray());
			 
			// System.out.println(finalHTMl);
			 return null;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
		
	}
	
	
	
	@RequestMapping("/fetchToDatabase")
	public String getHtmlDataFetchTODATABASE(HttpServletResponse response)
	{
StringBuilder builder=new StringBuilder();
		
		String newHTML=null;
		try {
			
			Resource resource2 = resourceLoader.getResource("/WEB-INF/jsp/demo1.html");
			
			byte[] copyToByteArray = FileCopyUtils.copyToByteArray(resource2.getInputStream());
			 newHTML = new String(copyToByteArray, StandardCharsets.UTF_8);
			 
			 builder.append(newHTML);
				
				
				//GETTING PERSON LIST
				List<Person> personList = this.personRepo.findAll();
				System.out.println(personList.size());
				
				Map<String, String> mapLooping =new LinkedHashMap<String, String>();
				
				for(Person person : personList)
				{
					
					Resource resource3 = resourceLoader.getResource("/WEB-INF/jsp/demo2.html");
					byte[] copyToByteArray3 = FileCopyUtils.copyToByteArray(resource3.getInputStream());
					String newHTML2 = new String(copyToByteArray3, StandardCharsets.UTF_8);
					
					mapLooping.put("id", String.valueOf(person.getId()));
					mapLooping.put("firtName", person.getFirtName());
					mapLooping.put("lastName", person.getLastName());
					mapLooping.put("gender", person.getGender());
					mapLooping.put("country", person.getCountry());
					mapLooping.put("age", person.getAge());
					mapLooping.put("date", person.getDate());
					mapLooping.put("sampleId", person.getSampleId());
					
					newHTML2 = newHTML2.replace("{{" + "id" + "}}",  String.valueOf(person.getId()));
					newHTML2 = newHTML2.replace("{{" + "firtName" + "}}",  person.getFirtName());
					newHTML2 = newHTML2.replace("{{" + "lastName" + "}}",  person.getLastName());
					newHTML2 = newHTML2.replace("{{" + "gender" + "}}",  person.getGender());
					newHTML2 = newHTML2.replace("{{" + "country" + "}}",  person.getCountry());
					newHTML2 = newHTML2.replace("{{" + "age" + "}}",  person.getAge());
					newHTML2 = newHTML2.replace("{{" + "date" + "}}",  person.getDate());
					newHTML2 = newHTML2.replace("{{" + "sampleId" + "}}",  person.getSampleId());
					builder.append(newHTML2);
					
					newHTML2=null;
					
				}
				
			
			 
			 
			 //RESOURCE # DEMO_3
			 Resource resource4 = resourceLoader.getResource("/WEB-INF/jsp/demo3.html");
				byte[] copyToByteArray4 = FileCopyUtils.copyToByteArray(resource4.getInputStream());
				String newHTML4 = new String(copyToByteArray4, StandardCharsets.UTF_8);
				
				builder.append(newHTML4);
			 
			
				//DOWNLOADING AND TRANSMITION CODE TO PDF
			 System.out.println("******PERPARE TO FLY******");
			 
			 ByteArrayOutputStream target = new ByteArrayOutputStream();
			  
			  HtmlConverter.convertToPdf(builder.toString(), target);
			  ServletOutputStream outputStream = response.getOutputStream();
			  
			  response.setContentType("APPLICATION/OCTET-STREAM");   
			  response.setHeader("Content-Disposition","attachment; filename=\"" + "NewTEST1.pdf" + "\"");   
			  
			  outputStream.write(target.toByteArray());
			 return null;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
