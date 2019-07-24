package com.search.web;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.search.common.Constants;
import com.search.common.StatusCode;
import com.search.entity.User;
import com.search.exception.CustomException;
import com.search.model.LoginParam;
import com.search.model.Result;
import com.search.model.SignUpParam;
import com.search.model.UserInfo;
import com.search.service.JwtService;
import com.search.service.UserService;

@RestController
@RequestMapping("/user")
public class LoginController {
	private Logger log = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private JwtService jwtService;

	/**
	 * 회원가입
	 */
	@PostMapping("/signUp")
	public Result signUp(@RequestBody SignUpParam param) {
		Result result = new Result();
		result.setCode(StatusCode.SHOW_MESSAGE);
		
		param.setId(StringUtils.trimToNull(param.getId()));
		param.setPassword(StringUtils.trimToNull(param.getPassword()));
		param.setPassword2(StringUtils.trimToNull(param.getPassword2()));
		
		try {
			if(StringUtils.isEmpty(param.getId()) || StringUtils.isEmpty(param.getPassword()) || StringUtils.isEmpty(param.getPassword2())) {
				throw new CustomException(StatusCode.SHOW_MESSAGE, "요청 정보가 유효하지 않습니다.");
			}
			
			if(!StringUtils.equals(param.getPassword(), param.getPassword2())) {
				throw new CustomException(StatusCode.SHOW_MESSAGE, "비밀번호가 일치하지 않습니다.");
			}
			
			if(userService.getUser(param.getId()).isPresent()) {
				throw new CustomException(StatusCode.SHOW_MESSAGE, "존재하는 아이디입니다.");
			}
			
			User user = new User();
			user.setId(param.getId());
			
			// 암호화
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = passwordEncoder.encode(param.getPassword());
			log.info("pwd: {}", encodedPassword);
			user.setPwd(encodedPassword);
			
			userService.mergeUser(user);
		} catch (CustomException ce) {
			return new Result(ce.getCode(), ce.getMessage());
		} catch (Exception e) {
			log.error("signUp error: {}", e.toString());
			return new Result(StatusCode.INTERNAL_SERVER_ERROR, "알 수 없는 오류가 발생했습니다.");
		}
		
		return Result.successInstance();
	}
	
	/**
	 * 로그인
	 * @param param
	 * @return
	 */
	@PostMapping("/login")
	public UserInfo login(@RequestBody LoginParam param) {
		User user = userService.getUser(param.getId(), param.getPassword());
		
		Map<String, String> data = new HashMap<>();
		data.put("id", param.getId());
		String token = jwtService.create(Constants.JWT.LOGIN_SALT, Constants.JWT.LOGIN_DATA_KEY, data);
		// response.setHeader(HttpHeaders.AUTHORIZATION, token);
		
		UserInfo info = new UserInfo();
		info.setId(user.getId());
		info.setAccessToken(token);
		return info;
	}

	/**
	 * id 조회
	 * @return
	 */
	@GetMapping("/id")
	public String id() {
		Map<String, String> data = jwtService.getData(Constants.JWT.LOGIN_SALT, Constants.JWT.LOGIN_DATA_KEY);
		return data != null ? data.get("id") : null;
	}

}
