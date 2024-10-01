package org.mywork.stitchbe.dto;

import java.util.Date;

public class ReviewDTO {
	private Long academyId;             // 학원 ID
    private Long reviewId;             // 리뷰 ID
    private Long memberId;             // 회원 ID
    private Long courseId;             // 강좌 ID
    private String education;          // 강의 내용
    private int educationRating;       // 교육에 대한 별점 (1~5)
    private String instructor;         // 강사에 대한 리뷰
    private int instructorRating;      // 강사에 대한 별점 (1~5)
    private String facility;           // 시설에 대한 리뷰
    private int facilityRating;        // 시설에 대한 별점 (1~5)
    private String atmosphere;         // 분위기에 대한 리뷰
    private int atmosphereRating;      // 분위기에 대한 별점 (1~5)
    private String management;         // 행정 처리에 대한 리뷰
    private int managementRating;      // 행정에 대한 별점 (1~5)
    private String later;              // 사후 관리에 대한 리뷰
    private int laterRating;           // 사후 관리에 대한 별점 (1~5)
    
    private String educationSpec;      // 정보 (전공/비전공/유사 전공) (선택)
    private String employmentStatus;   // 취업 여부 ('Y' = 취업, 'N' = 미취업)
    private Date regDate;         // 리뷰 작성일
    private Date editDate;        // 리뷰 수정일
 
    private String courseName;  // 코스 이름 추가
    private String academyName; // 학원 이름 추가
    // Getters and Setters
   

    public Long getAcademyId() {
		return academyId;
	}

	public void setAcademyId(Long academyId) {
		this.academyId = academyId;
	}

    public Long getReviewId() {
        return reviewId;
    }

	public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public int getEducationRating() {
        return educationRating;
    }

    public void setEducationRating(int educationRating) {
        this.educationRating = educationRating;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public int getInstructorRating() {
        return instructorRating;
    }

    public void setInstructorRating(int instructorRating) {
        this.instructorRating = instructorRating;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public int getFacilityRating() {
        return facilityRating;
    }

    public void setFacilityRating(int facilityRating) {
        this.facilityRating = facilityRating;
    }

    public String getAtmosphere() {
        return atmosphere;
    }

    public void setAtmosphere(String atmosphere) {
        this.atmosphere = atmosphere;
    }

    public int getAtmosphereRating() {
        return atmosphereRating;
    }

    public void setAtmosphereRating(int atmosphereRating) {
        this.atmosphereRating = atmosphereRating;
    }

    public String getManagement() {
        return management;
    }

    public void setManagement(String management) {
        this.management = management;
    }

    public int getManagementRating() {
        return managementRating;
    }

    public void setManagementRating(int managementRating) {
        this.managementRating = managementRating;
    }

    public String getLater() {
        return later;
    }

    public void setLater(String later) {
        this.later = later;
    }

    public int getLaterRating() {
        return laterRating;
    }

    public void setLaterRating(int laterRating) {
        this.laterRating = laterRating;
    }

    public String getEducationSpec() { 
        return educationSpec; 
    }

    public void setEducationSpec(String educationSpec) { 
        this.educationSpec = educationSpec; 
    }

    
    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getAcademyName() {
		return academyName;
	}

	public void setAcademyName(String academyName) {
		this.academyName = academyName;
	}

    
}
