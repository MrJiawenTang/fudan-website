package com.cloud.cqc.client.cms.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aliyuncs.exceptions.ClientException;
import com.cloud.cqc.client.common.controller.CRUDController;
import com.cloud.cqc.framework.persistence.dto.Searchable;
import com.cloud.cqc.service.meeting.entity.Meeting;
import com.cloud.cqc.service.meeting.service.IMeetingParticipantsService;
import com.cloud.cqc.service.meeting.service.IMeetingService;
import com.cloud.cqc.service.meeting.vo.MeetingParticipantsVO;
import com.cloud.cqc.service.meeting.vo.MeetingVO;

/**
 * 会议管理
 * 
 * @author Joy.zhou
 *
 */
@RestController
@RequestMapping("/cms/meeting")
public class CmsMeetingController extends CRUDController<Meeting, MeetingVO, Searchable, IMeetingService> {

	@Autowired
	private IMeetingParticipantsService meetingParticipantsService;

	/**
	 * 通知参会人员
	 * 
	 * @param meetingId
	 * @return
	 * @throws ClientException
	 */
	@RequestMapping(value = "/{id}/notify", method = RequestMethod.POST)
	public Object notify(@PathVariable Long id) {
		this.baseService.notifyMeeting(id, getCurrentUser().getId());
		return resultOk();
	}

	/**
	 * 导入参会人员
	 * 
	 * @param importParticpantVO
	 * @return
	 */
	@RequestMapping(value = "/{id}/importParticipants", method = RequestMethod.POST)
	public Object importParticipants(@PathVariable Long id, @RequestParam MultipartFile file) {
		InputStream is = null;
		Workbook workBook = null;
		try {
			is = file.getInputStream();
			workBook = WorkbookFactory.create(is);
		} catch (IOException | InvalidFormatException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		List<MeetingParticipantsVO> mpList = new ArrayList<>();
		HSSFSheet sheet = (HSSFSheet) workBook.getSheetAt(0);
		// 从第二行读入
		int lastRowNum = sheet.getLastRowNum();
		for (int i = 1; i <= lastRowNum; i++) {
			HSSFRow row = sheet.getRow(i);
			// 读取每一行数据封装 VO
			HSSFCell nameCell = row.getCell(0);
			HSSFCell mobileCell = row.getCell(1);
			mobileCell.setCellType(CellType.STRING);
			HSSFCell emailCell = row.getCell(2);
			HSSFCell sexCell = row.getCell(3);
			String name = nameCell.getStringCellValue();
			String mobile = mobileCell.getStringCellValue();
			String email = emailCell.getStringCellValue();
			String sex = sexCell.getStringCellValue();
			MeetingParticipantsVO mpVo = new MeetingParticipantsVO();
			mpVo.setName(name);
			mpVo.setMobile(mobile);
			mpVo.setMail(email);
			mpVo.setMeetingId(id);
			mpVo.setSex("男".equals(sex) ? 1 : 0);
			mpList.add(mpVo);

		}
		this.meetingParticipantsService.insert(mpList);

		return resultOk();
	}

	/**
	 * 列出参会人员
	 * 
	 * @param meetingId
	 * @return
	 */
	@RequestMapping(value = "/{id}/participants", method = RequestMethod.GET)
	public Object listMeetingParticipants(@PathVariable Long id) {
		List<MeetingParticipantsVO> mpList = this.meetingParticipantsService.selectByMeetingId(id);
		return resultOk(mpList);
	}

	/**
	 * 更新参会人员
	 * 
	 * @param meetingParticipantsVO
	 * @return
	 */
	@RequestMapping(value = "/participant/{id}", method = RequestMethod.PUT)
	public Object updateParticipant(@RequestBody MeetingParticipantsVO meetingParticipantsVO) {
		meetingParticipantsService.update(meetingParticipantsVO);
		return resultOk();
	}

	/**
	 * 删除参会人员
	 * 
	 * @param meetingParticipantId
	 * @return
	 */
	@RequestMapping(value = "/participant/{id}", method = RequestMethod.DELETE)
	public Object deleteParticipant(@PathVariable Long id) {
		meetingParticipantsService.deleteById(id);
		return resultOk();
	}

}
