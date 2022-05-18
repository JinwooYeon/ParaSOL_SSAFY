import { Components } from "components/Components";
import { JwtHeader, PasswordUpdateRequest } from "model/Model";

export const UserPATCH = () => {
  ////////////// 입력해야하는 부분 ///////////
  const API = {
    uri: "/user",
    method: "PATCH",
    detail: "비밀번호 수정",
    completed: true,
  };
  const requestBody = {
    PasswordUpdateRequest,
    JwtHeader,
  };
  ///////////////////////////////////

  return (
    <>
      <Components API={API} requestBody={requestBody} />
    </>
  );
};
