import { useState } from "react";
import { Stack, Divider, Grid, Box, Button } from "@mui/material";
import { InputController } from "components/InputController";
import { useForm, SubmitHandler } from "react-hook-form";
import axios from "axios";
import { Completed } from "components/Completed";
import { RequestBody } from "components/RequestBody";

export const UserPOST = () => {
  const API = {
    uri: "/client",
    method: "POST",
    detail: "회원가입",
    completed: true,
  };
  const requestBody = {
    ClientInfo: [
      {
        value: "id",
        type: "string",
        required: false,
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

  let defaultObject = {};
  Object.values(requestBody).map((val) => {
    val.map((v) => {
      const tempObj = { [v.value]: "" };
      defaultObject = Object.assign(defaultObject, tempObj);
    });
  });

  const [show, setShow] = useState(false);
  const [output, setOutput] = useState("");
  const [formData, setFormData] = useState(defaultObject);
  // const [formData, setFormData] = useState({
  //   id: "",
  //   password: "",
  //   name: "",
  // });

  const handlShow = () => {
    setShow(!show);
  };

  const onSubmit: SubmitHandler<any> = async (data) => {
    console.log(data);
    const inputData = data;
    await axios
      .post("/client", inputData, {
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

const styles = {
  api: {
    // margin: "auto 10px",
    padding: "2px 40px",
  },
  apiHeader: {
    width: "100%",
    justifyContent: "start",
    color: "black",
  },
  apiUri: {
    color: "blue",
    fontSize: 15,
    alignItems: "center",
    textTransform: "lowercase",
  },
  apiMethod: {
    background: "green",
    color: "white",
    padding: "4px 10px",
    fontSize: 13,
    borderRadius: 20,
  },
  apiDetail: {
    fontSize: 20,
    fontWeight: "bold",
    padding: 4,
    alignItems: "center",
  },
  apiContent: {
    color: "black",
    fontWeight: "bold",
  },
  apiContentDetail: {
    backgroundColor: "#D8D6D6",
    borderRadius: 10,
    margin: "15px auto",
    padding: 13,
    width: "100%",
    height: "100%",
  },
  outputStyle: {
    border: "solid green 3px",
    padding: 9,
    width: "90%",
    height: 217,
  },
};
