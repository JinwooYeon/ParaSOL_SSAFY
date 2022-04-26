import { useState } from "react";
import { Stack, Divider, Box, Button } from "@mui/material";
import { SubmitHandler } from "react-hook-form";
import axios from "axios";
import { Completed } from "components/Completed";
import { RequestBody } from "components/RequestBody";
import styles from "components/styles";

export const AuthBioPOST = () => {
  ////////////// 입력해야하는 부분 ///////////
  const API = {
    uri: "/auth/bio",
    method: "POST",
    detail: "생체인증(지문) 등록 및 요청",
    completed: false,
  };
  const requestBody = {
    DeviceIdentifier: [
      {
        value: "device_serial_num",
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

  const handlShow = () => {
    setShow(!show);
  };

  const onSubmit: SubmitHandler<any> = async (data) => {
    console.log(data);
    const inputData = data.DeviceIdentifier;
    const token = JSON.stringify(data.jwt);
    await axios
      .post(API.uri, inputData, {
        headers: {
          token,
        },
      })
      .then((response) => {
        console.log(response);
        setOutput(response.data);
      })
      .catch((err) => {
        console.log(err);
      });
  };

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
            <Box style={styles.apiMethod}>{API.method}</Box>
            <Box sx={styles.apiUri}>{API.uri}</Box>
          </Stack>
        </Stack>
      </Button>
      {show ? (
        <Stack>
          <Divider sx={{ mt: 1, mb: 3 }} />
          <Stack spacing={2} sx={styles.api}>
            <Stack direction="column">
              <Box style={styles.apiContent}>API 요청</Box>
              <Box style={styles.apiContentDetail}>
                <Stack direction="column" spacing={3}>
                  <RequestBody
                    requestBody={requestBody}
                    formData={formData}
                    setFormData={setFormData}
                  />
                  <Button
                    variant="contained"
                    type="button"
                    color="success"
                    onClick={() => onSubmit(formData)}
                  >
                    출력값 확인하기
                  </Button>
                </Stack>
              </Box>
            </Stack>
            <Stack justifyContent="center" alignItems="center">
              <Box sx={{ fontWeight: "bold" }}>출력값</Box>
              <Box style={styles.outputStyle}>
                <p>{output}</p>
              </Box>
            </Stack>
          </Stack>
        </Stack>
      ) : null}
    </>
  );
};
