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
  // localStorage
  const accessToken = localStorage.getItem("accessToken")
    ? localStorage.getItem("accessToken")
    : "";
  const refreshToken = localStorage.getItem("refreshToken")
    ? localStorage.getItem("refreshToken")
    : "";

  // useState
  const [show, setShow] = useState(false);
  const [formData, setFormData] = useState({});
  const [responseData, setResponseData] = useState({
    status: "",
    output: "",
  });

  // method
  const handleShow = () => {
    setShow(!show);
  };
  const isRefresh = (detail: string) => {
    if (detail === "새로운 인증 토큰 요청") {
      return refreshToken;
    } else {
      return accessToken;
    }
  };
  const onSubmit = async (data: any) => {
    switch (props.API.method) {
      // GET 요청
      case "GET":
        // 구글 로그인 (Oauth)
        if (props.API.uri === "/user/login/google") {
          // 수정 필요
          console.log("login");
          window.open("http://k6s101.p.ssafy.io:8080/user/login/google");
          // axios({
          //   method: "get",
          //   url: "http://k6s101.p.ssafy.io:8080/user/login/google",
          // })
          //   .then((res) => console.log(res))
          //   .catch((err) => console.log(err));
        } else {
          await axios({
            method: "get",
            url: props.API.uri,
            headers: { Authorization: `Bearer ${isRefresh(props.API.detail)}` },
            params: data,
          })
            .then((response) => {
              if (props.API.detail === "새로운 인증 토큰 요청") {
                localStorage.setItem("accessToken", response.data.accessToken);
                localStorage.setItem(
                  "refreshToken",
                  response.data.refreshToken
                );
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
        }
        break;
      // POST 요청
      case "POST":
        if (props.API.detail === "입금" || props.API.detail === "결제") {
          data["accountTo"] = { accountNumber: data.accountNumber };
          delete data.accountNumber;
        }
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
      // PATCH 요청
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
      // DELETE 요청
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
            if (props.API.detail === "회원 탈퇴") {
              localStorage.clear();
            }
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
      {/* 요청할 api */}
      <DropdownButton API={props.API} handleShow={handleShow} />
      {show ? (
        <Stack>
          <Divider sx={{ mt: 1, mb: 3 }} />
          <Stack spacing={2} sx={styles.apiSection}>
            <Stack direction="column">
              <Box>
                <Stack direction="column" spacing={3}>
                  {/* 요청 */}
                  <Request
                    requestBody={props.requestBody}
                    formData={formData}
                    setFormData={setFormData}
                    onSubmit={onSubmit}
                  />
                </Stack>
              </Box>
            </Stack>
            {/* 응답 */}
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
