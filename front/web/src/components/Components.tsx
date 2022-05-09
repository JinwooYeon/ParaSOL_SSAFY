import { useState } from "react";
import { Box, Divider, Stack } from "@mui/material";
import { DropdownButton } from "./DropdownButton";
import { Request } from "./Request";
import { Response } from "./Response";
import styles from "./styles";
import axios from "axios";

interface PropsType {
  API: any;
  requestBody: any;
}

export const Components: React.FC<PropsType> = (props: PropsType) => {
  // const BASE_URL = "http://k6S101.p.ssafy.io:8080/";
  const accessToken = localStorage.getItem("accessToken")
    ? localStorage.getItem("accessToken")
    : "";
  const refreshToken = localStorage.getItem("refreshToken")
    ? localStorage.getItem("refreshToken")
    : "";
  const [show, setShow] = useState(false);
  const [formData, setFormData] = useState({});
  const [responseData, setResponseData] = useState({
    status: "",
    output: "",
  });

  const handleShow = () => {
    setShow(!show);
  };
  const onSubmit = async (data: any) => {
    switch (props.API.method) {
      case "GET":
        await axios({
          method: "get",
          url: props.API.uri,
          headers: { Authorization: `Bearer ${accessToken}` },
          params: data,
        })
          .then((response) => {
            console.log(response);
            setResponseData({
              status: response.status.toString(),
              output: JSON.stringify(response.data),
            });
          })
          .catch((err) => {
            setResponseData({
              status: err.response.status.toString(),
              output: err.toString(),
            });
          });
        break;
      case "POST":
        await axios({
          method: "post",
          url: props.API.uri,
          headers: { Authorization: `Bearer ${accessToken}` },
          data,
        })
          .then((response) => {
            if (props.API.detail === "로그인") {
              localStorage.setItem("accessToken", response.data.accessToken);
              localStorage.setItem("refreshToken", response.data.refreshToken);
            }
            setResponseData({
              status: response.status.toString(),
              output: JSON.stringify(response.data),
            });
          })
          .catch((err) => {
            setResponseData({
              status: err.response.status.toString(),
              output: err.toString(),
            });
          });
        break;
      case "PATCH":
        await axios({
          method: "patch",
          url: props.API.uri,
          headers: { Authorization: `Bearer ${accessToken}` },
          data,
        })
          .then((response) => {
            setResponseData({
              status: response.status.toString(),
              output: JSON.stringify(response.data),
            });
          })
          .catch((err) => {
            setResponseData({
              status: err.response.status.toString(),
              output: err.toString(),
            });
          });
        break;
      case "DELETE":
        await axios({
          method: "delete",
          url: props.API.uri,
          headers: { Authorization: `Bearer ${accessToken}` },
          data,
        })
          .then((response) => {
            setResponseData({
              status: response.status.toString(),
              output: JSON.stringify(response.data),
            });
          })
          .catch((err) => {
            setResponseData({
              status: err.response.status.toString(),
              output: err.toString(),
            });
          });
        break;
      default:
        console.log("axios error");
    }
  };

  return (
    <>
      <DropdownButton API={props.API} handleShow={handleShow} />
      {show ? (
        <Stack>
          <Divider sx={{ mt: 1, mb: 3 }} />
          <Stack spacing={2} sx={styles.apiSection}>
            <Stack direction="column">
              <Box>
                <Stack direction="column" spacing={3}>
                  <Request
                    requestBody={props.requestBody}
                    formData={formData}
                    setFormData={setFormData}
                    onSubmit={onSubmit}
                  />
                </Stack>
              </Box>
            </Stack>
            <Response
              responseData={responseData}
              setResponseData={setResponseData}
            />
          </Stack>
        </Stack>
      ) : null}
    </>
  );
};
