package com.tester.tester.Common;

import com.tester.tester.Constants.ServerInfoConstants;
import com.tester.tester.Entity.ResultEntity;
import com.tester.tester.Service.ResultCodeService;

public class ResultFactory {


    public static ResultEntity buildResult(int resultCode, String message, Object data) {
        return new ResultEntity(resultCode, message, data);
    }

    public static ResultEntity executeSuccess(Object data) {
        return  executeResult(ResultCodeService.SUCCESS, "成功", data);
    }

    public static ResultEntity executeFail() {
        return executeResult(ResultCodeService.FAIL, ServerInfoConstants.SERVER_FAIl_INFO, null);
    }

    public static ResultEntity executeFail(String message) {
        return executeResult(ResultCodeService.FAIL, message, null);
    }

    public static ResultEntity executeError() {
        return executeResult(ResultCodeService.SERVER_ERROR, ServerInfoConstants.SERVER_ERROR_INFO, null);
    }

    public static ResultEntity executeUnAuthorized() {
        return executeResult(ResultCodeService.UNAUTHORIZED, ServerInfoConstants.SERVER_UNAUTHORIZED_INFO, null);
    }

    public static ResultEntity executeResult(ResultCodeService resultCode, String message, Object data) {
        return buildResult(resultCode.code, message, data);
    }

}
