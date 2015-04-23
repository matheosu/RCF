package rcfmodel.util;

import java.util.Locale;

import rcfmodel.enums.OSType;

public abstract class SystemUtil {
	
	/** Divisor's */
	private static final String SLASH = "/";
	@SuppressWarnings("unused")
	private static final String DOUBLE_SLASH = "//";
	private static final String BACK_SLASH = "\\";
	@SuppressWarnings("unused")
	private static final String DOUBLE_BACK_SLASH ="\\\\";
	
	private static OSType detectedOS;

	private static OSType whatOperationSystem() {
		/** OperationSystem */
		final String OS = System.getProperty("os.name","generic").toLowerCase(Locale.ENGLISH);;
		
		if (OS == null){
			SystemUtil.detectedOS = OSType.UNKNOW;
		
		}else if(OS.indexOf("win")>=0){
			SystemUtil.detectedOS = OSType.WINDOWS;
		
		}else if(OS.indexOf("mac")>=0 || OS.indexOf("darwin")>=0){
			SystemUtil.detectedOS = OSType.MAC;
		
		}else if(OS.indexOf("nux")>=0 || OS.indexOf("nix")>=0 || OS.indexOf("aix")>0){
			SystemUtil.detectedOS = OSType.LINUX;
		}else{
			SystemUtil.detectedOS = OSType.UNKNOW;
		}
		
		return SystemUtil.detectedOS;
	}

	public static String getDivisor(){
		OSType os;
		if(SystemUtil.getDetectedOS()== null)
			os = SystemUtil.whatOperationSystem();
		else
			os = SystemUtil.getDetectedOS();
		
		switch (os) {
		case WINDOWS:
			return BACK_SLASH;

		case MAC:
			return SLASH;
		case LINUX:
			return SLASH;
		default:
			return BACK_SLASH;
		}
	}
	
	public static OSType getDetectedOS(){
		return SystemUtil.detectedOS;
	}
}
