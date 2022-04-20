import AccountBalanceGET from "components/account/accountBalanceGET";
import AccountGET from "components/account/accountGET";
import AccountHistoryGET from "components/account/accountHistoryGET";
import { Box, Paper, Stack } from "@mui/material";
import { styled } from "@mui/material/styles";

const Item = styled(Paper)(({ theme }) => ({
  backgroundColor: theme.palette.mode === "dark" ? "#1A2027" : "#fff",
  ...theme.typography.body2,
  padding: theme.spacing(1),
  textAlign: "left",
  color: theme.palette.text.secondary,
}));

export const Account = () => {
  return (
    <>
      <Box sx={{ width: "100%" }}>
        <Stack spacing={2}>
          <Item>
            <AccountGET />
          </Item>
          <Item>
            <AccountBalanceGET />
          </Item>
          <Item>
            <AccountHistoryGET />
          </Item>
        </Stack>
      </Box>
    </>
  );
};
