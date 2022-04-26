import { useState } from "react";
import { Stack, Divider, Box, Button } from "@mui/material";
import { SubmitHandler } from "react-hook-form";
import axios from "axios";
import { Completed } from "components/Completed";
import { RequestBody } from "components/RequestBody";
import styles from "components/styles";
import { ApiMethod } from "components/Components.styled";

export const AccountHistoryGET = () => {
  ////////////// 입력해야하는 부분 ///////////
  const API = {
    uri: "/account/history",
    method: "GET",
    detail: "계좌 거래내역 조회",
    completed: false,
  };
  const requestBody = {
    AuthorizedClientAccountInfo: [
      {
        value: "access_token",
        type: "string",
        required: true,
      },
      {
        value: "account_info",
        type: "string",
        required: true,
      },
    ],
    token: [
      {
        value: "jwt",
        type: "string",
        required: true,
      },
    ],
  };
  ///////////////////////////////////

  const [show, setShow] = useState(false);
  const [output, setOutput] = useState("");
  const [formData, setFormData] = useState({});
  const [responseStatus, setResponseStatus] = useState<number>();

  const handlShow = () => {
    setShow(!show);
  };

  const onSubmit: SubmitHandler<any> = async (data) => {
    console.log(data);
    const token = JSON.stringify(data.jwt);
    await axios
      .get(API.uri, {
        headers: {
          token,
        },
      })
      .then((response) => {
        console.log(response);
        setResponseStatus(response.status);
        setOutput(response.data.toString());
      })
      .catch((err) => {
        setResponseStatus(err.response.status);
        setOutput(err.toString());
        console.log(err);
      });
  };

  // const showMethod = (m: string) => {
  //   console.log(m);
  //   if (m === "POST")
  //     return <Box style={styles.apiMethod.post}>{API.method}</Box>;
  //   else if (m === "PATCH")
  //     return <Box style={styles.apiMethod.patch}>{API.method}</Box>;
  //   else if (m === "DELETE")
  //     return <Box style={styles.apiMethod.delete}>{API.method}</Box>;
  //   else if (m === "GET")
  //     return <Box style={styles.apiMethod.get}>{API.method}</Box>;
  //   return <Box style={styles.apiMethod.least}>{API.method}</Box>;
  // };

  return (
    <>
      <Button sx={styles.apiHeader} onClick={handlShow}>
        <Stack
          direction="row"
          justifyContent="space-between"
          sx={{ width: "100%" }}
        >
          <Stack direction="row" alignItems="center" spacing={2}>
            <Completed completed={API.completed} />
            <Box style={styles.apiDetail}>{API.detail}</Box>
          </Stack>
          <Stack direction="row" alignItems="center" spacing={2}>
            {/* <Box>{showMethod(API.method)}</Box> */}
            <Box sx={styles.apiUri}>{API.uri}</Box>
            <ApiMethod method={API.method}>{API.method}</ApiMethod>
          </Stack>
        </Stack>
      </Button>
      {show ? (
        <Stack>
          <Divider sx={{ mt: 1, mb: 3 }} />
          <Stack spacing={2} sx={styles.apiSection}>
            <Stack direction="column">
              <Box>
                <Stack direction="column" spacing={3}>
                  <RequestBody
                    requestBody={requestBody}
                    formData={formData}
                    setFormData={setFormData}
                  />
                  <Button
                    variant="contained"
                    type="button"
                    color="primary"
                    onClick={() => onSubmit(formData)}
                  >
                    출력값 확인하기
                  </Button>
                </Stack>
              </Box>
            </Stack>
            <Stack justifyContent="center" alignItems="center">
              <Stack
                direction="row"
                justifyContent="space-between"
                alignItems="center"
                style={styles.outputHeader}
              >
                <Box sx={{ fontWeight: "bold", fontSize: 20 }}>출력값</Box>
                <Stack direction="row" spacing={2}>
                  <Box>응답코드</Box>
                  {responseStatus === 201 || responseStatus === 200 ? (
                    <Box style={styles.responseStatus.success}>
                      {responseStatus && responseStatus}
                    </Box>
                  ) : (
                    <Box style={styles.responseStatus.failed}>
                      {responseStatus && responseStatus}
                    </Box>
                  )}
                </Stack>
              </Stack>
              <Box style={styles.outputStyle}>
                <Box sx={{ wordWrap: "break-word" }}>{output}</Box>
              </Box>
            </Stack>
          </Stack>
        </Stack>
      ) : null}
    </>
  );
};
