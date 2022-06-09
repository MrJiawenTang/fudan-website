package com.cloud.cqc.client.ueditor.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.util.FileCopyUtils;

import com.cloud.cqc.client.ueditor.constant.AppInfo;
import com.cloud.cqc.client.ueditor.entity.ResultState;

public class StorageManager {
	public static final int BUFFER_SIZE = 8192;

	public StorageManager() {
	}

	public static ResultState saveBinaryFile(byte[] data, String path) {
		File file = new File(path);

		ResultState state = valid(file);

		if (!state.isSuccess()) {
			return state;
		}
		try {
			FileCopyUtils.copy(data, new FileOutputStream(file));
		} catch (IOException e) {
			return new ResultState(false, AppInfo.IO_ERROR);
		}
		state.setState(file.getAbsolutePath());
		state.setSize(new Long(data.length));
		state.setTitle(file.getName());
		return state;
	}

	public static ResultState saveFileByInputStream(InputStream in, String path, long... maxSizes) {

		File file = new File(path);

		ResultState state = valid(file);

		try {
			FileCopyUtils.copy(in, new FileOutputStream(file));
			state = new ResultState(true, AppInfo.SUCCESS);
			state.setSize(file.length());
			state.setTitle(file.getName());
		} catch (IOException e) {
			new ResultState(false, AppInfo.IO_ERROR);
		}
		if (maxSizes.length > 0) {
			if (file.length() > maxSizes[0]) {
				file.delete();
				return new ResultState(false, AppInfo.MAX_SIZE);
			}
		}

		return state;
	}

	private static ResultState valid(File file) {
		File parentPath = file.getParentFile();

		if ((!parentPath.exists()) && (!parentPath.mkdirs())) {
			return new ResultState(false, AppInfo.FAILED_CREATE_FILE);
		}

		if (!parentPath.canWrite()) {
			return new ResultState(false, AppInfo.PERMISSION_DENIED);
		}

		return new ResultState(true, AppInfo.SUCCESS);
	}
}
