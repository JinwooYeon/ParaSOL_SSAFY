import { useState } from "react";
import { Stack, Divider, Grid, Box, Button } from "@mui/material";
import { InputController } from "components/InputController";
import { useForm, SubmitHandler } from "react-hook-form";
import axios from "axios";

export const AuthPOST = () => {
  const [show, setShow] = useState(false);
  const [output, setOutput] = useState("");
  const { handleSubmit, control } = useForm({});

  const handlShow = () => {
    setShow(!show);
  };

  const onSubmit: SubmitHandler<any> = async (data) => {
    console.log(data);
    const inputData = JSON.stringify(data);
    await axios
      .post("/auth", inputData, {
        headers: {
          "content-Type": "application/json",
        },
      })
      .then((response) => {
        console.log(response);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  return (
    <>
      <Button sx={styles.apiHeader} onClick={handlShow}>
        로그인 _ AuthPOST
      </Button>
      {show ? (
        <Stack>
          <Divider sx={{ mt: 1, mb: 3 }} />
          <Stack spacing={2} sx={{ p: 2 }}>
            <Stack direction="row" justifyContent="space-between">
              <Stack
                direction="row"
                justifyContent="start"
                alignItems="center"
                spacing={2}
              >
                <div style={styles.apiTitle}>/auth</div>
                <div style={styles.apiMethod}>GET</div>
              </Stack>
              <div style={styles.apiDetail}>로그인</div>
            </Stack>
            <Grid container spacing={3}>
              <Grid item xs={5}>
                <Stack direction="column">
                  <Box style={styles.apiContent}>API 요청</Box>
                  <Box style={styles.apiContentDetail}>
                    <Box>요청 모델명</Box>
                    <Box>종류</Box>
                    <Box>타입</Box>
                    <Box>속성명</Box>
                    <Box>속성타입</Box>
                    <Box>헤더</Box>
                  </Box>
                </Stack>
              </Grid>
              <Grid item xs={5}>
                <Stack direction="column">
                  <Box style={styles.apiContent}>API 응답</Box>
                  <Box style={styles.apiContentDetail}>
                    <Box>반환 모델명</Box>
                    <Box>반환 설명</Box>
                    <Box>반환 코드</Box>
                  </Box>
                </Stack>
              </Grid>
            </Grid>
            <Grid container spacing={3}>
              <Grid item xs={5}>
                <form
                  onSubmit={handleSubmit(onSubmit)}
                  style={styles.inputStyle}
                >
                  <Stack
                    direction="column"
                    justifyContent="space-between"
                    alignItems="center"
                  >
                    <InputController control={control} />
                    <Button type="submit">출력값 확인하기</Button>
                  </Stack>
                </form>
              </Grid>
              <Grid item xs={5}>
                <Stack alignItems="center">
                  <Box style={styles.outputStyle}>
                    <p>{output}</p>
                  </Box>
                  <Box sx={{ marginTop: "25px" }}>출력값</Box>
                </Stack>
              </Grid>
            </Grid>
          </Stack>
        </Stack>
      ) : null}
    </>
  );
};

const styles = {
  apiHeader: {
    width: "100%",
    justifyContent: "start",
    color: "black",
  },
  apiTitle: {
    color: "blue",
    fontSize: 25,
    padding: 4,
    alignItems: "center",
  },
  apiMethod: {
    background: "green",
    color: "white",
    padding: 2,
    fontSize: 13,
  },
  apiDetail: {
    color: "grey",
    fontSize: 15,
    padding: 4,
    alignItems: "center",
  },
  apiContent: {
    color: "black",
    fontWeight: "bold",
  },
  apiContentDetail: {
    backgroundColor: "#D8D6D6",
    margin: "10px auto",
    padding: 9,
    width: "90%",
    height: 250,
  },
  inputStyle: {
    height: 250,
  },
  outputStyle: {
    border: "solid green 3px",
    padding: 9,
    width: "90%",
    height: 217,
  },
};
