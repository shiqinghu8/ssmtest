package com.cydiguo.ssm.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cydiguo.ssm.entity.User;
import com.cydiguo.ssm.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private IUserService userService;

	@RequestMapping("/users")
	public String list(Model model) {
		List<User> users = this.userService.getListUser();
		model.addAttribute("users", users);
		return "user/list";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String show(@PathVariable int id, Model model) {
		User user = this.userService.getUserById(id);
		model.addAttribute(user);
		return "user/show";
	}

	// 链接到add页面时是GET请求，会访问这段代码
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(@ModelAttribute("user") User user) {
		// 开启modelDriven
		// model.addAttribute(new User());
		return "user/add";
	}

	// 在具体添加用户时，是post请求，就访问以下代码
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@Validated User user, BindingResult br, @RequestParam("attach") MultipartFile attach,
			HttpServletRequest req) throws IOException {// 一定要紧跟Validate之后写验证结果类
		if (br.hasErrors()) {
			// 如果有错误直接跳转到add视图
			return "user/add";
		}
		// 文件上传
		String realpath = req.getSession().getServletContext().getRealPath("/resources/upload");
		System.out.println(realpath);

		if (!attach.isEmpty()) {
			File f = new File(realpath + "/" + attach.getOriginalFilename());
			FileUtils.copyInputStreamToFile(attach.getInputStream(), f);
			user.setFile_name(attach.getOriginalFilename());
		}
		// 文件上传end

		this.userService.saveUser(user);
		return "redirect:/user/users";
	}

	@RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
	public String update(@PathVariable int id, Model model) {
		User user = this.userService.getUserById(id);
		model.addAttribute(user);
		return "user/update";
	}

	@RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
	public String update(@PathVariable String id, @Validated User user, BindingResult br,
			@RequestParam("attach") MultipartFile attach, HttpServletRequest req) throws IOException {
		if (br.hasErrors()) {
			// 如果有错误直接跳转到update视图
			return "user/update";
		}
		// 文件上传
		String realpath = req.getSession().getServletContext().getRealPath("/resources/upload");
		System.out.println(realpath);

		if (!attach.isEmpty()) {
			File f = new File(realpath + "/" + attach.getOriginalFilename());
			FileUtils.copyInputStreamToFile(attach.getInputStream(), f);
			user.setFile_name(attach.getOriginalFilename());
		}
		// 文件上传end

		this.userService.updateByPrimaryKey(user);
		return "redirect:/user/users";
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable int id) {
		this.userService.deleteByPrimaryKey(id);
		return "redirect:/user/users";
	}

	// json
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, params = "json")
	@ResponseBody
	public User show(@PathVariable int id) {
		User user = this.userService.getUserById(id);
		return user;
	}

	// 文件上传
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String upload() {
		return "user/upload";
	}

	// 文件上传
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(@RequestParam("attachs") MultipartFile[] attachs, HttpServletRequest req) throws IOException {
		// 文件上传
		String realpath = req.getSession().getServletContext().getRealPath("/resources/upload");
		System.out.println(realpath);
		for (MultipartFile attach : attachs) {
			if (attach.isEmpty())
				continue;
			File f = new File(realpath + "/" + attach.getOriginalFilename());
			FileUtils.copyInputStreamToFile(attach.getInputStream(), f);
		}
		String message = "success";
		return "redirect:/user/uploadjson?json&message=" + message;
	}

	// json
	@RequestMapping(value = "/uploadjson", method = RequestMethod.GET, params = "json")
	@ResponseBody
	public Map<String, Object> uploadjson(String message) {
		// 文件上传end
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", message);
		return map;
	}

	// 下载文件
	@RequestMapping("/download")
	public String download(String fileName, HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition",
				"attachment;fileName=" + new String(fileName.getBytes("gbk"), "iso-8859-1"));// 转码之后下载的文件名称不会出现中文乱码
		try {
			String path = request.getSession().getServletContext().getRealPath("/resources/upload");
			InputStream inputStream = new FileInputStream(new File(path + File.separator + fileName));

			OutputStream os = response.getOutputStream();
			byte[] b = new byte[2048];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}

			// 这里要关闭。
			os.close();

			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 返回值要注意，要不然就出现下面这句错误！
		// java+getOutputStream() has already been called for this response
		return null;
	}

	//返回字符串
	//使用@ResponseBody注解
	@RequestMapping(value = "getString", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getString() {
		String ss = "汉字123abc";
		return ss;
	}

}
