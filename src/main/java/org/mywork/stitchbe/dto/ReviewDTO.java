package org.mywork.stitchbe.dto;

import java.util.Date;

public class ReviewDTO {

	private Long reviewId; // 리뷰 ID
	private Long memberId; // 작성자 (회원) ID
	private Long courseId; // 강의 ID

	private String education; // 교육 내용
	private Double educationRating; // 교육 평가 (평점)

	private String instructor; // 강사
	private Double instructorRating;// 강사 평가 (평점)

	private String facility; // 시설
	private Double facilityRating; // 시설 평가 (평점)

	private String atmosphere; // 분위기
	private Double atmosphereRating;// 분위기 평가 (평점)

	private String management; // 운영 관리
	private Double managementRating;// 운영 관리 평가 (평점)

	private String later; // 향후 계획
	private Double laterRating; // 향후 계획에 대한 평가 (평점)

	private String educationSpec; // 교육 특이 사항
	private String employmentStatus;// 고용 상태

	private Date regDate; // 등록일
	private Date editDate; // 수정일

	// Getter와 Setter 메서드들
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

	public Double getEducationRating() {
		return educationRating;
	}

	public void setEducationRating(Double educationRating) {
		this.educationRating = educationRating;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public Double getInstructorRating() {
		return instructorRating;
	}

	public void setInstructorRating(Double instructorRating) {
		this.instructorRating = instructorRating;
	}

	public String getFacility() {
		return facility;
	}

	public void setFacility(String facility) {
		this.facility = facility;
	}

	public Double getFacilityRating() {
		return facilityRating;
	}

	public void setFacilityRating(Double facilityRating) {
		this.facilityRating = facilityRating;
	}

	public String getAtmosphere() {
		return atmosphere;
	}

	public void setAtmosphere(String atmosphere) {
		this.atmosphere = atmosphere;
	}

	public Double getAtmosphereRating() {
		return atmosphereRating;
	}

	public void setAtmosphereRating(Double atmosphereRating) {
		this.atmosphereRating = atmosphereRating;
	}

	public String getManagement() {
		return management;
	}

	public void setManagement(String management) {
		this.management = management;
	}

	public Double getManagementRating() {
		return managementRating;
	}

	public void setManagementRating(Double managementRating) {
		this.managementRating = managementRating;
	}

	public String getLater() {
		return later;
	}

	public void setLater(String later) {
		this.later = later;
	}

	public Double getLaterRating() {
		return laterRating;
	}

	public void setLaterRating(Double laterRating) {
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
}
