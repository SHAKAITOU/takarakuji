package sha.framework.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import sha.framework.data.OutputBaseData;
import sha.framework.exception.TKRKAPIException;
import sha.framework.exception.TKRKException;
import sha.framework.exception.TKRKScreenException;

@ControllerAdvice
public class APIExceptionResolver extends ResponseEntityExceptionHandler{

	@ExceptionHandler(value = TKRKAPIException.class)
    public ResponseEntity<Object> commercePFAPIExceptionHandler(Exception ex) {


		OutputBaseData output = new OutputBaseData();
		output.setHttpStatus(HttpStatus.FORBIDDEN);

		System.out.println("### catch API error ###");

        return new ResponseEntity<Object>(output, HttpStatus.FORBIDDEN);
    }

	@ExceptionHandler(value = TKRKScreenException.class)
	public ModelAndView commercePFScreenExceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception ex) {

		System.out.println("### catch 画面 error ###");

		return null;
   }

	@ExceptionHandler(value = TKRKException.class)
	public ModelAndView commercePFExceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception ex) {

		System.out.println("### catch 共通 error ###");

		return null;
   }

//	@ExceptionHandler(value = Exception.class)
//	public ModelAndView exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception ex) {
//
//		System.out.println("### catch 予期せぬ error ###");
//		System.out.println(ex.toString());
//
//		return null;
//    }



}
