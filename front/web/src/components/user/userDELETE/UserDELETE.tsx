import { Components } from "components/Components";
import { Token } from "model/Model";

export const UserDELETE = () => {
  ////////////// 입력해야하는 부분 ///////////
  const API = {
    uri: "/user",
    method: "DELETE",
    detail: "회원 탈퇴",
    completed: false,
  };
  const requestBody = {
    Token,
  };
  ///////////////////////////////////

  return (
    <>
      <Components API={API} requestBody={requestBody} />
    </>
  );
};
