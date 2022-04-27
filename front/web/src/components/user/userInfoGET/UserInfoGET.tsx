import { Components } from "components/Components";

export const UserInfoGET = () => {
  ////////////// 입력해야하는 부분 ///////////
  const API = {
    uri: "/client/my-info",
    method: "GET",
    detail: "내 정보 조회",
    completed: false,
  };
  const requestBody = {
    token: [
      {
        value: "jwt",
        type: "string",
        required: true,
      },
    ],
  };
  ///////////////////////////////////

  return (
    <>
      <Components API={API} requestBody={requestBody} />
    </>
  );
};
