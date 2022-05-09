import { Components } from "components/Components";
import { UserUpdateRequest, JwtHeader } from "model/Model";

export const UserPATCH = () => {
  ////////////// 입력해야하는 부분 ///////////
  const API = {
    uri: "/user",
    method: "PATCH",
    detail: "회원 수정",
    completed: true,
  };
  const requestBody = {
    UserUpdateRequest,
    JwtHeader,
  };
  ///////////////////////////////////

  return (
    <>
      <Components API={API} requestBody={requestBody} />
    </>
  );
};
