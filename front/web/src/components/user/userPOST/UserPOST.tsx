import { useState } from "react";
import { Stack, Divider, Box, Button } from "@mui/material";
import { SubmitHandler } from "react-hook-form";
import axios from "axios";
import { Completed } from "components/Completed";
import { RequestBody } from "components/RequestBody";
import styles from "components/styles";

export const UserPOST = () => {
  ////////////// 입력해야하는 부분 ///////////
  const API = {
    uri: "/client",
    method: "POST",
    detail: "회원가입",
    completed: false,
  };
  const requestBody = {
    ClientInfo: [
      {
        value: "id",
        type: "string",
        required: true,
      },
      {
        value: "password",
        type: "string",
        required: true,
      },
      {
        value: "name",
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
    const inputData = data;
    await axios
      .post(API.uri, inputData, {
        headers: {
          "content-Type": "application/json",
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
          </Stack>
        </Stack>
      ) : null}
    </>
  );
};
