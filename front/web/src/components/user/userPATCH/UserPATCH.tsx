import { Components } from "components/Components";
import { UserInfo, Token } from "model/Model";

export const UserPATCH = () => {
  ////////////// 입력해야하는 부분 ///////////
  const API = {
    uri: "/user",
    method: "PATCH",
    detail: "회원 수정",
    completed: false,
  };
  const requestBody = {
    UserInfo,
    Token,
  };
  ///////////////////////////////////

  return (
    <>
      <Components API={API} requestBody={requestBody} />
    </>
  );
};
