import { Box, Stack, TextField, IconButton, Grid, Button } from "@mui/material";
import DeleteIcon from "@mui/icons-material/Delete";
import ContentCopyIcon from "@mui/icons-material/ContentCopy";
import styles from "./styles";
import { CopyToClipboard } from "react-copy-to-clipboard";
import React, { useState } from "react";
import styled from "styled-components";
import Snackbar, { SnackbarOrigin } from "@mui/material/Snackbar";
import Alert from "@mui/material/Alert";

interface IMyprops {
  requestBody: any;
  formData: any;
  setFormData: (formDate: any) => void;
  onSubmit: (a: any) => void;
}
interface State extends SnackbarOrigin {
  open: boolean;
}

export const Request: React.FC<IMyprops> = ({
  requestBody,
  formData,
  setFormData,
  onSubmit,
}) => {
  // localStorage
  const accessToken = localStorage.getItem("accessToken");
  const refreshToken = localStorage.getItem("refreshToken");

  // useState
  // 복사
  const [state, setState] = useState({ value: "copy\ntext", copied: false });
  const onCopy = () => {
    setState({ ...state, copied: true });
    setAlert({ ...alert, open: true });
  };
  // 복사 확인 메시지
  const [alert, setAlert] = useState<State>({
    open: false,
    vertical: "bottom",
    horizontal: "center",
  });
  const { vertical, horizontal, open } = alert;
  const handleClose = () => {
    setAlert({ ...alert, open: false });
  };

  // method
  const handleChange = (key: string, v: string) => {
    setFormData({
      ...formData,
      [key]: v,
    });
    setState({ value: v, copied: false });
  };
  const clearValue = (key: string) => {
    setFormData({
      ...formData,
      [key]: "",
    });
  };

  return (
    <>
      {requestBody ? (
        <>
          <Box>
            {Object.keys(requestBody).map((key: string) =>
              requestBody[key].map((re: any, index: string) => {
                return (
                  <Grid
                    container
                    rowSpacing={2}
                    columnSpacing={3}
                    sx={{ width: "100%", marginTop: 1 }}
                    alignItems="center"
                    key={index}
                  >
                    <Grid item xs={5}>
                      <Stack direction="row" spacing={1} sx={{ width: "40%" }}>
                        <div style={{ width: "100%", whiteSpace: "normal" }}>
                          {/* 속성명 */}
                          {re.value && (
                            <div style={styles.apiTitle}>{re.value}</div>
                          )}
                          {/* 속성타입 */}
                          {re.type && (
                            <div style={styles.apiType}>{re.type}</div>
                          )}
                        </div>
                      </Stack>
                    </Grid>
                    <Grid item xs={1.5}>
                      {/* 필수 여부 */}
                      {re.required ? (
                        <div style={styles.apiRequired.required}>required</div>
                      ) : (
                        <div style={styles.apiRequired.notRequired}>
                          not required
                        </div>
                      )}
                    </Grid>
                    <Grid item xs={5.5}>
                      <FlexBox>
                        {/* 데이터 입력 */}
                        <TextField
                          label={re.value}
                          disabled={
                            re.value === "accessToken" ||
                            re.value === "refreshToken"
                              ? true
                              : false
                          }
                          size="small"
                          type="text"
                          sx={{ width: "100%" }}
                          onChange={(
                            e: React.ChangeEvent<HTMLInputElement>
                          ) => {
                            handleChange(re.value, e.target.value);
                          }}
                          value={
                            re.value === "accessToken" ||
                            re.value === "refreshToken"
                              ? accessToken || refreshToken
                                ? re.value === "accessToken"
                                  ? accessToken
                                  : refreshToken
                                : "로그인을 해야합니다!"
                              : formData[re.value] || ""
                          }
                          InputProps={{
                            endAdornment: (
                              <IconButton
                                size="small"
                                onClick={() => clearValue(re.value)}
                              >
                                <DeleteIcon />
                              </IconButton>
                            ),
                          }}
                        />
                        <CopyBox>
                          <CopyToClipboard
                            onCopy={onCopy}
                            options={{ message: "Whoa!" }}
                            text={state.value}
                          >
                            <IconButton size="small" onClick={onCopy}>
                              <ContentCopyIcon />
                            </IconButton>
                          </CopyToClipboard>
                        </CopyBox>
                      </FlexBox>
                    </Grid>
                  </Grid>
                );
              })
            )}
          </Box>{" "}
          <Button
            variant="outlined"
            color="error"
            onClick={() => setFormData({})}
          >
            입력값 초기화
          </Button>
          <Button
            variant="contained"
            type="button"
            color="primary"
            onClick={() => onSubmit(formData)}
          >
            출력값 확인하기
          </Button>
          <Snackbar
            anchorOrigin={{ vertical, horizontal }}
            open={open}
            onClose={handleClose}
            autoHideDuration={2000}
            key={vertical + horizontal}
          >
            <Alert
              onClose={handleClose}
              variant="outlined"
              severity="success"
              sx={{ width: "100%" }}
            >
              클립보드에 복사되었습니다.
            </Alert>
          </Snackbar>
        </>
      ) : null}
    </>
  );
};

const FlexBox = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
`;
const CopyBox = styled.div`
  margin-left: 4%;
`;
