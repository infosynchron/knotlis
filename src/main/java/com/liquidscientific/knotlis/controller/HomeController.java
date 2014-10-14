package com.liquidscientific.knotlis.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.liquidscientific.knotlis.entity.Narcotics;
import com.liquidscientific.knotlis.entity.Patient;
import com.liquidscientific.knotlis.entity.User;
import com.liquidscientific.knotlis.restful.RestFullService;
import com.liquidscientific.knotlis.service.PatientService;
import com.liquidscientific.knotlis.service.UserService;
import com.liquidscientific.knotlis.utilities.FileUploadForm;
import com.liquidscientific.knotlis.utilities.KnotLISUtils2;

/**
 * Handles requests for the application home page.
 */
@Controller
@Scope("session")
@SessionAttributes({ "user", "patient" })
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private PatientService patientService;

	@Autowired
	private RestFullService restfullService;

	@Autowired
	ServletContext servletContext;

	private String clientid = "rcs";
	ModelAndView mav = new ModelAndView();
	private User user = new User();
	Calendar calendar = Calendar.getInstance();
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	private Patient patient = new Patient();
	private String reportselected = "-Select a Report to View-";

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView landing(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		mav.setViewName("main");
		return mav;

	}

	@RequestMapping(value = "/nav", method = RequestMethod.POST)
	public ModelAndView processNavigation(
			@RequestParam("navmode") String navmode) {
		if (navmode.equalsIgnoreCase("dashboard")) {

			mav.setViewName("main");
			return mav;
		} else if (navmode.equalsIgnoreCase("patiententry")) {

			mav.setViewName("patiententry");
			return mav;
		} else if (navmode.equalsIgnoreCase("patientreport")) {

			mav.setViewName("patientreport");
			return mav;
		} else {
			mav.setViewName("main");
			return mav;
		}
	}

	@RequestMapping(value = "/patientreport", method = RequestMethod.POST)
	public ModelAndView processPatientReport(
			@RequestParam("patientreportmode") String patientreportmode,
			@RequestParam("mrnreporttxt") String mrnreporttxt,
			@RequestParam("reportselected") String reportselected) {
		if (patientreportmode.equalsIgnoreCase("patientsearch")) {

			List<String> patientreports = patientService
					.getPatientReports(mrnreporttxt);
			mav.addObject("patientreports", patientreports);
			mav.setViewName("patientreport");
			return mav;
		} else if (patientreportmode.equalsIgnoreCase("getreport")) {
			System.out.println(reportselected);
			return new ModelAndView("patientTestView", "patienttestlist",
					patientService.getPatientTest(reportselected));
		} else {
			mav.setViewName("patientreport");
			return mav;
		}
	}

	@RequestMapping(value = "/patiententry", method = RequestMethod.POST)
	public ModelAndView processPatientEntry(
			@RequestParam("patiententrymode") String patiententrymode,
			@RequestParam("mrnsearchtxt") String mrnsearchtxt,
			@RequestParam(required = false, value = "bzo") Boolean bzo,
			@RequestParam(required = false, value = "amp") Boolean amp,
			@RequestParam(required = false, value = "thc") Boolean thc,
			@RequestParam(required = false, value = "opi") Boolean opi,
			@RequestParam(required = false, value = "coc") Boolean coc,
			@RequestParam(required = false, value = "met") Boolean met,
			@RequestParam(required = false, value = "bup") Boolean bup,
			@RequestParam(required = false, value = "oxy") Boolean oxy,
			@ModelAttribute("uploadForm") FileUploadForm uploadForm, Model map) {
		if (patiententrymode.equalsIgnoreCase("patientsearch")) {

			patient = patientService.getPatient(mrnsearchtxt);
			mav.addObject("patient", patient);
			mav.setViewName("patiententry");
			return mav;
		} else if (patiententrymode.equalsIgnoreCase("patienttest")) {
			System.out.println(patient.getClientid());

			String destfilepath = "";
			String fileindex = "";
			String month = "";

			try {
				List<MultipartFile> files = uploadForm.getFiles();
				if (null != files && files.size() > 0) {
					for (MultipartFile multipartFile : files) {
						if (KnotLISUtils2.getCurrentMonth() < 10) {
							month = "0"
									+ KnotLISUtils2.getCurrentMonth()
											.toString();
						} else {
							month = KnotLISUtils2.getCurrentMonth().toString();
						}
						String originalfilename = multipartFile
								.getOriginalFilename();
						fileindex = "KL"
								+ patient.getClientid().toString().trim() + "_"
								+ KnotLISUtils2.getCurrentYear() + "_" + month
								+ "_" + KnotLISUtils2.getCurrentDay() + "_"
								+ KnotLISUtils2.getTimeStamp();
						String extension = originalfilename.substring(
								originalfilename.lastIndexOf("."),
								originalfilename.length());
						destfilepath = "C:\\knotlis\\" + fileindex + extension;
						multipartFile.transferTo(new File(destfilepath));
					}
				}
			} catch (IOException e) {
				System.out.println(e.toString());
				e.printStackTrace();
			}

			Narcotics narcotics = new Narcotics();

			narcotics.setClientid(patient.getClientid());
			narcotics.setMrn(patient.getMrn());

			if (bzo != null) {
				narcotics.setBzo("N");
			} else {
				narcotics.setBzo("Y");
			}

			if (amp != null) {
				narcotics.setAmp("N");
			} else {
				narcotics.setAmp("Y");
			}

			if (thc != null) {
				narcotics.setThc("N");
			} else {
				narcotics.setThc("Y");
			}

			if (opi != null) {
				narcotics.setOpi("N");
			} else {
				narcotics.setOpi("Y");
			}

			if (coc != null) {
				narcotics.setCoc("N");
			} else {
				narcotics.setCoc("Y");
			}

			if (oxy != null) {
				narcotics.setOxy("N");
			} else {
				narcotics.setOxy("Y");
			}

			if (bup != null) {
				narcotics.setBup("N");
			} else {
				narcotics.setBup("Y");
			}

			if (met != null) {
				narcotics.setMet("N");
			} else {
				narcotics.setMet("Y");
			}

			narcotics.setFilepath(destfilepath);
			narcotics.setFileindex(fileindex);
			narcotics.setTestdate(new java.sql.Timestamp(new java.util.Date()
					.getTime()));

			patientService.savePatientTest(narcotics);

			mav.setViewName("patiententry");

			mav.addObject("patient", patient);
			return mav;

		}

		else {
			mav.setViewName("patiententry");
			return mav;
		}

	}

	@RequestMapping(value = "/rest/{mrn}/{patientfirstname}/{patientlastname}/{gender}/{thc}/{coc}/{amp}/{opi}/{met}/{bzo}/{oxy}/{bup}/{imagename}/{imagestorepath}/{dateofbirth}", produces = "application/json")
	@ResponseBody
	public String addPatientTest(@PathVariable("mrn") String mrn,
			@PathVariable("patientfirstname") String patientfirstname,
			@PathVariable("patientlastname") String patientlastname,
			@PathVariable("gender") String gender,
			@PathVariable("thc") String thc, @PathVariable("coc") String coc,
			@PathVariable("amp") String amp, @PathVariable("opi") String opi,
			@PathVariable("met") String met, @PathVariable("bzo") String bzo,
			@PathVariable("oxy") String oxy, @PathVariable("bup") String bup,
			@PathVariable("imagename") String imagename,
			@PathVariable("imagestorepath") String imagestorepath,
			@PathVariable("dateofbirth") String dateofbirth

	) throws IOException {
		// java.sql.Date.valueOf(

		String fileindex = KnotLISUtils2.getFileIndex(mrn, clientid);
		// String destfilepath = "C:/knotlis" + "/" + "repository" + "/" +
		// fileindex + KnotLISUtils2.getFileExtension(imagename);

		// KnotLISUtils2.copyFileUsingApacheCommonsIO(new File(imagestorepath +
		// "/" + imagename), new File(destfilepath));

		Patient patient = new Patient();
		patient.setClientid(clientid);
		patient.setDateofbirth(java.sql.Date.valueOf(KnotLISUtils2
				.convertUSToMySQLFormat(dateofbirth.trim())));
		patient.setGender(gender);
		patient.setMrn(mrn);
		patient.setPatientfirstname(patientfirstname);
		patient.setPatientid(mrn);
		patient.setPatientlastname(patientlastname);
		patient.setSampletype("Urine");
		patient.setDateofbirth(java.sql.Date.valueOf(KnotLISUtils2
				.convertUSToMySQLFormat(dateofbirth)));

		Narcotics narcotics = new Narcotics();
		narcotics.setMrn(mrn);
		narcotics.setAmp(amp);
		narcotics.setBup(bup);
		narcotics.setBzo(bzo);
		narcotics.setCoc(coc);
		narcotics.setMet(met);
		narcotics.setOpi(opi);
		narcotics.setOxy(oxy);
		narcotics.setThc(thc);

		narcotics.setClientid(clientid);
		narcotics.setFileindex(fileindex);
		narcotics.setFilepath(imagestorepath);
		narcotics.setTestdate(new java.sql.Timestamp(new java.util.Date()
				.getTime()));

		System.out.println(imagename);
		return restfullService.addPatientTest(patient, narcotics);

	}

}
