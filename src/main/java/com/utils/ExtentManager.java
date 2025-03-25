package com.utils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        String timeStamp = DateUtil.getDateInyyyyMMddhhmmssFormat();
        if (extent == null) {
            String reportPath = System.getProperty("user.dir") + "/target/ExtentReport"+timeStamp+".html";
            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
            extent = new ExtentReports();
            extent.attachReporter(reporter);
            reporter.config().setDocumentTitle("Jupiter toys Project");
            reporter.config().setReportName("Jupiter toys Test Project ");
            reporter.config().setTheme(Theme.STANDARD);
        }
        return extent;
    }
}
